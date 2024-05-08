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
    @JoinColumn(name = "type")
    private int idAnimalType;
    @ManyToOne(targetEntity = Provider.class)
    private int idProvider;
}
