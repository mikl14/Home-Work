package ru.mtsbank.fintech.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "habitats")
@Entity
public class Habitats implements Serializable {
    @Id
    private int idArea;
    private String area;
}
