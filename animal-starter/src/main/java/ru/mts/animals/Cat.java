package ru.mts.animals;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cat extends Pet {

    public Cat(String name, String character, String essentialFood) {
        super(name, character, essentialFood);
    }

    public Cat(String breed, String name, String character, LocalDate birthDate, BigDecimal cost, String essentialFood, int lifeSpan, String secretInformation) {
        super(breed, name, character, birthDate, cost, essentialFood, lifeSpan, secretInformation);
    }

    public Cat() {
        super();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + super.toString();
    }
}
