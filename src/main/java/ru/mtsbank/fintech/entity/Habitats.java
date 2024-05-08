package ru.mtsbank.fintech.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Habitats implements Serializable {
    @Id
    private int idArea;
    private String area;
}
