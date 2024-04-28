package ru.mtsbank.fintech.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AnimalType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idType;

    private String type;
    private boolean isWild;

    public AnimalType(String type, boolean isWild) {
        this.type = type;
        this.isWild = isWild;
    }

    public AnimalType() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isWild() {
        return isWild;
    }

    public void setWild(boolean wild) {
        isWild = wild;
    }

    @Override
    public String toString() {
        return "AnimalType{" +
                "idType=" + idType +
                ", type='" + type + '\'' +
                ", isWild=" + isWild +
                '}';
    }
}
