package ru.mtsbank.fintech.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Habitats {
    @Id
    private int idArea;
    private String area;
}
