package ru.mts.Animals;

import java.math.BigDecimal;

public class Bear extends Predator {
    public Bear(String breed, String name, String character, BigDecimal cost, String livingEnvironment, int weight) {
        super(breed, name, character, cost, livingEnvironment, weight);
    }

    @Override
    public String toString() {
        return "i'm Bear! my name:" + name +
                " livingEnvironment='" + livingEnvironment + '\'' +
                ", Weight=" + Weight +
                ", breed='" + breed + '\'' +
                ", character='" + character + '\'' +
                ", cost=" + cost +
                '}';
    }
}
