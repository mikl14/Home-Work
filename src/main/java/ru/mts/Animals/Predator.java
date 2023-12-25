package ru.mts.Animals;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Predator extends AbstractAnimal
{
    protected String livingEnvironment;
    protected int weight;

    public Predator(String breed, String name, String character, LocalDate birthDate, BigDecimal cost, String livingEnvironment, int weight) {
        super(breed, name,birthDate, character, cost);
        this.livingEnvironment = livingEnvironment;
        this.weight = weight;
    }

    public String getLivingEnvironment() {
        return livingEnvironment;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "my name: " + name +
                " my birth date: "+getFormatDate("dd-MM-yyyy") +'\'' +
                " livingEnvironment='" + livingEnvironment + '\'' +
                ", Weight=" + weight +
                ", breed='" + breed + '\'' +
                ", character='" + character + '\'' +
                ", cost=" + cost +
                '}';
    }
}
