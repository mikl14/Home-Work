package ru.mtsbank.fintech.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AnimalsProvider {
    @Id
    private int idAnimalType;
    private int idProvider;
}
