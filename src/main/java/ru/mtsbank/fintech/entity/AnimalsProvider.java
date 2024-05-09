package ru.mtsbank.fintech.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class AnimalsProvider implements Serializable {
    @Id
    @ManyToOne(targetEntity = AnimalType.class)
    @JoinColumn(name = "id_animal_type")
    private int idAnimalType;
    @ManyToOne(targetEntity = Provider.class)
    @JoinColumn(name = "id_provider")
    private int idProvider;
}
