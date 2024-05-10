package ru.mtsbank.fintech.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "animals_habitats")
@Entity
public class AnimalsHabitats implements Serializable {
    @Id
    @ManyToOne(targetEntity = AnimalType.class)
    @JoinColumn(name = "id_animal_type")
    private int idAnimalType;
    @ManyToOne(targetEntity = Habitats.class)
    @JoinColumn(name = "id_area")
    private int idArea;
}
