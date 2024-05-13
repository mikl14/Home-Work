package ru.mtsbank.fintech.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "animal_type")
@Entity
public class AnimalType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id_type")
    private int idType;
    private String type;
    private boolean isWild;

    @OneToMany(targetEntity = Animal.class, fetch = FetchType.EAGER)
    private List<Animal> animalList = new ArrayList<>();

    public AnimalType(String type, boolean isWild, List<Animal> animalList) {
        this.type = type;
        this.isWild = isWild;
        this.animalList = animalList;
    }

    public AnimalType() {
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public boolean addToAnimalList(Animal animal) {
        return animalList.add(animal);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isWild() {
        return isWild;
    }

    public void setWild(boolean wild) {
        isWild = wild;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        var animalObj = ((AnimalType) obj);
        return Objects.equals(type, animalObj.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return type;
    }
}
