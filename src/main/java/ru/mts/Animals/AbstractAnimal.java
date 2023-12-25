package ru.mts.Animals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

public abstract class AbstractAnimal implements Animal,Comparable
{
    protected LocalDate birthDate  ;
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
    public AbstractAnimal(String breed, String name,LocalDate birthDate, String character, BigDecimal cost) {
        this.breed = breed;
        this.name = name;
        this.birthDate = birthDate;
        this.character = character;
        this.cost = cost.setScale(2, BigDecimal.ROUND_HALF_UP);
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

    @Override
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if(obj == null || getClass() != obj.getClass()) return false;

        return (name.equals(((AbstractAnimal) obj).name) &&
                birthDate.equals(((AbstractAnimal) obj).birthDate) &&
                breed.equals(((AbstractAnimal) obj).breed));
    }

    @Override
    public int compareTo(Object o) {
        return birthDate.compareTo(((AbstractAnimal)o).birthDate);
    }
}
