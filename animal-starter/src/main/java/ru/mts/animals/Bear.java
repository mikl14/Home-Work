package ru.mts.animals;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Bear extends Predator {

    public Bear(String name, String character, String livingEnvironment) {
        super(name, character, livingEnvironment);
    }

    public Bear() {
        super();
    }

    public Bear(String breed, String name, String character, LocalDate birthDate, BigDecimal cost, String livingEnvironment, int weight,String secretInformation) {
        super(breed, name, character, birthDate, cost, livingEnvironment, weight,secretInformation);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + super.toString();
    }
}
