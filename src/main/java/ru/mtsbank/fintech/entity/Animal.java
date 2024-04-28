package ru.mtsbank.fintech.entity;

import javax.persistence.*;

@Table(name = "animal")
@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(targetEntity = Breed.class)
    @JoinColumn(name="breed")
    private Breed breed;
    @ManyToOne(targetEntity = AnimalType.class)
    @JoinColumn(name="type")
    private AnimalType animalType;

    @ManyToOne(targetEntity = Creature.class)
    @JoinColumn(name="creature")
    private Creature creature;

    public Animal(Breed breed, AnimalType animalType, Creature creature) {
        this.breed = breed;
        this.animalType = animalType;
        this.creature = creature;
    }

    public Animal() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Creature getCreature() {
        return creature;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", breed=" + breed +
                ", animalType=" + animalType +
                ", creature=" + creature +
                '}';
    }
}
