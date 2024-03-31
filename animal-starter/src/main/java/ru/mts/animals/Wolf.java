package ru.mts.animals;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Wolf extends Predator {

    public Wolf(String name, String character, String livingEnvironment) {
        super(name, character, livingEnvironment);
    }

    public Wolf() {
        super();
    }

    public Wolf(String breed, String name, String character, LocalDate birthDate, BigDecimal cost, String livingEnvironment, int weight) {
        super(breed, name, character, birthDate, cost, livingEnvironment, weight);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + super.toString();
    }
}
