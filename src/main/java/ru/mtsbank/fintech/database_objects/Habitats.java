package ru.mtsbank.fintech.database_objects;

import org.springframework.stereotype.Component;

@Component
public class Habitats implements TableRecord {
    private int idArea;
    private String area;

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int id_area) {
        this.idArea = id_area;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "{" +
                "id_area=" + idArea +
                ", area='" + area + '\'' +
                '}';
    }
}
