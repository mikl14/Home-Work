package ru.mts.Animals;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cat extends Pet
{

    public Cat(String breed, String name, String character, LocalDate birthDate, BigDecimal cost, String essentialFood, int lifeSpan) {
        super(breed, name, character,birthDate, cost, essentialFood, lifeSpan);
    }

    @Override
    public String toString() {
        return "I'm cat! " + super.toString();
    }
}
