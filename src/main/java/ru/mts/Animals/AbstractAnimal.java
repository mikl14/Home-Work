package ru.mts.Animals;

import java.math.BigDecimal;

public abstract class AbstractAnimal implements Animal
{
    protected String breed,name,character;
    protected BigDecimal cost;

    /**
     * Конструктор AbstractAnimal
     * @param breed порода животного
     * @param name имя животного
     * @param character характер животного
     * @param cost цена животного
     *
     *             cost округляется до 2х знаков после запятой, для округления использован метод Math.round() т.к в т.з предполагается что в параметре cost храниться сразу округленная цена для магазинов
     */
    public AbstractAnimal(String breed, String name, String character, BigDecimal cost) {
        this.breed = breed;
        this.name = name;
        this.character = character;
        this.cost = BigDecimal.valueOf(Double.valueOf(Math.round(cost.doubleValue()*100))/100);
    }

    @Override
    public String getBreed() {
        return breed;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getCost() {return cost;}

    @Override
    public String getCharacter() {
        return character;
    }
}