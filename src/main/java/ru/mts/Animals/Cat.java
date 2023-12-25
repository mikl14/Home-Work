package ru.mts.Animals;

import java.math.BigDecimal;

public class Cat extends Pet
{

    public Cat(String breed, String name, String character, BigDecimal cost, String essentialFood, int lifeSpan) {
        super(breed, name, character, cost, essentialFood, lifeSpan);
    }

    @Override
    public String toString() {
        return "I'm cat! my name:+" + name +
                " , essentialFood='" + essentialFood + '\'' +
                ", lifeSpan=" + lifeSpan +
                ", breed='" + breed + '\'' +
                ", character='" + character + '\'' +
                ", cost=" + cost +
                '}';
    }
}
