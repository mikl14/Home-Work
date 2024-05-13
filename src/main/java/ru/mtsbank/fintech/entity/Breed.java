package ru.mtsbank.fintech.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "breed")
@Entity
public class Breed implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "breed", targetEntity = Animal.class)
    private List<Animal> animalList = new ArrayList<>();

    public Breed(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public Breed() {
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    @Override
    public String toString() {
        return "Breed{" +
                "id=" + id +
                '}';
    }
}
