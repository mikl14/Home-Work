package ru.mts.Animals;

import java.math.BigDecimal;

public abstract class Pet extends AbstractAnimal
{
    protected String essentialFood;

    protected int lifeSpan;

    public Pet(String breed, String name, String character, BigDecimal cost, String essentialFood, int lifeSpan) {
        super(breed, name, character, cost);
        this.essentialFood = essentialFood;
        this.lifeSpan = lifeSpan;
    }

    public String getEssentialFood() {
        return essentialFood;
    }

    public int getLifeSpan() {
        return lifeSpan;
    }
}
