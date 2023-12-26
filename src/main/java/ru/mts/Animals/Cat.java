package ru.mts.Animals;

import ru.mts.animalsCreators.AnimalFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

public class Cat extends Pet {

    public Cat(String character, String essentialFood) {
        super(character, essentialFood);
    }

    public Cat(String breed, String name, String character, LocalDate birthDate, BigDecimal cost, String essentialFood, int lifeSpan) {
        super(breed, name, character, birthDate, cost, essentialFood, lifeSpan);
    }

    @Override
    public String toString() {
        return "I'm cat! " + super.toString();
    }
}
