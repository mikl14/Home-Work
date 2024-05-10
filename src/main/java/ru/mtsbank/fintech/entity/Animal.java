package ru.mtsbank.fintech.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Table(name = "animal")
@Entity
public class Animal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(targetEntity = Breed.class)
    @JoinColumn(name = "breed")
    private Breed breed;
    @ManyToOne(targetEntity = AnimalType.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "type")
    private AnimalType animalType;

    protected LocalDate birthDate;
    protected String name, character;
    protected BigDecimal cost;

    public Animal(Breed breed, AnimalType animalType) {
        this.breed = breed;
        this.animalType = animalType;
    }

    public Animal() {
    }

    public Animal(String name, String character, BigDecimal cost, AnimalType animalType) {
        this.name = name;
        this.character = character;
        this.cost = cost;
        this.animalType = animalType;
    }

    public Animal(String character, String name, LocalDate birthDate, BigDecimal cost) {
        this.name = name;
        this.character = character;
        this.cost = cost;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    @Override
    public boolean equals(Object obj) { // будут равны если равны имена, даты рождения и порода

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        var animalObj = ((Animal) obj);

        return Objects.equals(name, animalObj.name)
                && Objects.equals(birthDate, animalObj.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthDate);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", breed=" + breed +
                ", animalType=" + animalType +
                ", birthDate=" + birthDate +
                ", name='" + name + '\'' +
                ", character='" + character + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
