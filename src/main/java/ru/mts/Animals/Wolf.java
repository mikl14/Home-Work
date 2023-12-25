package ru.mts.Animals;

import java.math.BigDecimal;

public class Wolf extends Predator{
    public Wolf(String breed, String name, String character, BigDecimal cost, String livingEnvironment, int weight) {
        super(breed, name, character, cost, livingEnvironment, weight);
    }

    @Override
    public String toString() {
        return "i'm Big Bad Wolf! my name:" + name +
                " livingEnvironment='" + livingEnvironment + '\'' +
                ", Weight=" + weight +
                ", breed='" + breed + '\'' +
                ", character='" + character + '\'' +
                ", cost=" + cost +
                '}';
    }
}
