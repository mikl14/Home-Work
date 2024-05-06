package ru.mtsbank.fintech.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Provider {
    @Id
    private int idProvider;
    private String name;
    private String phone;
}
