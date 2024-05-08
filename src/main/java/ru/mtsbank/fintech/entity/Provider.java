package ru.mtsbank.fintech.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Provider implements Serializable {
    @Id
    private int idProvider;
    private String name;
    private String phone;
}
