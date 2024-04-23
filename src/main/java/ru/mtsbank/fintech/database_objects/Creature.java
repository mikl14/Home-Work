package ru.mtsbank.fintech.database_objects;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Creature implements TableRecord {
    private BigDecimal id;
    private String name;
    private int typeId, age;

    public Creature(BigDecimal id, String name, int typeId, int age) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
        this.age = age;
    }

    public Creature() {

    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                ", age=" + age +
                '}';
    }
}
