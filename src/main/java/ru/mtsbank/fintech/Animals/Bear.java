package ru.mtsbank.fintech.Animals;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Bear extends Predator {

    public Bear(String name,String character, String livingEnvironment) {
        super(name,character, livingEnvironment);
    }

    public Bear(String breed, String name, String character, LocalDate birthDate, BigDecimal cost, String livingEnvironment, int weight) {
        super(breed, name, character, birthDate, cost, livingEnvironment, weight);
    }

    @Override
    public String toString() {
        return "i'm Bear! " + super.toString();
    }
}
