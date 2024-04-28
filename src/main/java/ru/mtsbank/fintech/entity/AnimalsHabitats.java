package ru.mtsbank.fintech.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AnimalsHabitats {
    @Id
    private int idAnimalType;
    private int idArea;
}
