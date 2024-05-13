package ru.mtsbank.fintech.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "animals_provider")
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
