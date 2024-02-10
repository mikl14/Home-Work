package ru.mtsbank.fintech.Animals;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Predator extends AbstractAnimal {
    protected String livingEnvironment;
    protected int weight;

    /**
     * Predator конструктор
     *
     * @param character         характер животного
     * @param livingEnvironment ареал обитания
     */
    public Predator(String name, String character, String livingEnvironment) {
        super(name,character);
        this.livingEnvironment = livingEnvironment;
        this.weight = random.nextInt(150 - 80) + 80;
    }

    /**
     * Predator конструктор
     *
     * @param breed             порода животного
     * @param name              имя животного
     * @param birthDate         дата рождения
     * @param character         характер животного
     * @param cost              цена животного
     * @param livingEnvironment ареал обитания
     * @param weight            вес
     */
    public Predator(String breed, String name, String character, LocalDate birthDate, BigDecimal cost, String livingEnvironment, int weight) {
        super(breed, name, birthDate, character, cost);
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
                " my birth date: " + getFormatDate("dd-MM-yyyy") + '\'' +
                " livingEnvironment='" + livingEnvironment + '\'' +
                ", Weight=" + weight +
                ", breed='" + breed + '\'' +
                ", character='" + character + '\'' +
                ", cost=" + cost +
                '}';
    }
}
