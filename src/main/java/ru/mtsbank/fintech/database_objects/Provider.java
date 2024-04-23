package ru.mtsbank.fintech.database_objects;

import org.springframework.stereotype.Component;

@Component
public class Provider implements TableRecord {
    private int idProvider;
    private String name, phone;

    public int getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(int idProvider) {
        this.idProvider = idProvider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone.trim();
    }

    @Override
    public String toString() {
        return "{" +
                "idProvider=" + idProvider +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
