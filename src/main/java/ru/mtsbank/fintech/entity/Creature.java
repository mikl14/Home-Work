package ru.mtsbank.fintech.entity;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Creature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCreature;
    private String name;
    private int typeId;
    private int age;

    public Creature(String name, int typeId, int age) {
        this.name = name;
        this.typeId = typeId;
        this.age = age;
    }

    public Creature() {
    }

    @Override
    public String toString() {
        return "Creature{" +
                "idCreature=" + idCreature +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                ", age=" + age +
                '}';
    }
}
