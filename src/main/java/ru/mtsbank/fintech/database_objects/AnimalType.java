package ru.mtsbank.fintech.database_objects;

import org.springframework.stereotype.Component;

@Component
public class AnimalType implements TableRecord {
    private int idType;
    private String type;
    private boolean isWild;

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
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
        return "{" +
                "idType=" + idType +
                ", type='" + type + '\'' +
                ", isWild=" + isWild +
                '}';
    }
}
