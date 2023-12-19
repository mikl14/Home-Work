package ru.mts.Animals;

import java.math.BigDecimal;

public abstract class Predator extends AbstractAnimal
{
    protected String livingEnvironment;
    protected int weight;

    public Predator(String breed, String name, String character, BigDecimal cost, String livingEnvironment, int weight) {
        super(breed, name, character, cost);
        this.livingEnvironment = livingEnvironment;
        weight = weight;
    }

    public String getLivingEnvironment() {
        return livingEnvironment;
    }

    public int getWeight() {
        return weight;
    }

}
