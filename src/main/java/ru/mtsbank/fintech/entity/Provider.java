package ru.mtsbank.fintech.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "provider")
@Entity
public class Provider implements Serializable {
    @Id
    private int idProvider;
    private String name;
    private String phone;
}
