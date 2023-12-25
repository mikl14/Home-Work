package ru.mts.Animals;

import java.math.BigDecimal;

public class Fish extends Pet {
    public Fish(String breed, String name, String character, BigDecimal cost, String essentialFood, int lifeSpan) {
        super(breed, name, character, cost, essentialFood, lifeSpan);
    }

    @Override
    public String toString() {
        return "I'm fish! my name:" + name +
                ", essentialFood='" + essentialFood + '\'' +
                ", lifeSpan=" + lifeSpan +
                ", breed='" + breed + '\'' +
                ", character='" + character + '\'' +
                ", cost=" + cost +
                '}';
    }
}

