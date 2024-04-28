package ru.mtsbank.fintech.database_objects;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Creature {
    private BigDecimal id;
    private String name,type,area;
    private int age;

    public Creature(BigDecimal id, String name, String type, int age) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
    }

    public Creature() {

    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type.trim();
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
                ", type=" + type +
                ", age=" + age +
                ", area=" + area +
                '}';
    }
}
