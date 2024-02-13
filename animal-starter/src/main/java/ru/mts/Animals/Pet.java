package ru.mts.Animals;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Pet extends AbstractAnimal {
    protected String essentialFood;

    protected int lifeSpan;

    /**
     * Pet конструктор
     *
     * @param character     характер животного
     * @param essentialFood пища животного
     * @see AbstractAnimal
     */
    public Pet(String name, String character, String essentialFood) {
        super(name,character);
        this.essentialFood = essentialFood;
        this.lifeSpan = random.nextInt(12 - 9) + 9;
    }

    /**
     * Pet конструктор
     *
     * @param breed         порода животного
     * @param name          имя животного
     * @param birthDate     дата рождения
     * @param character     характер животного
     * @param cost          цена животного
     * @param essentialFood характер животного
     * @param lifeSpan      пища животного
     */
    public Pet(String breed, String name, String character, LocalDate birthDate, BigDecimal cost, String essentialFood, int lifeSpan) {
        super(breed, name, birthDate, character, cost);
        this.essentialFood = essentialFood;
        this.lifeSpan = lifeSpan;
    }

    public String getEssentialFood() {
        return essentialFood;
    }

    public int getLifeSpan() {
        return lifeSpan;
    }

    @Override
    public String toString() {
        return "my name: " + name
                + " my birth date: " + getFormatDate("dd-MM-yyyy") + '\''
                + " , essentialFood='" + essentialFood + '\'' + ", " +
                "lifeSpan=" + lifeSpan + ", breed='" + breed + '\''
                + ", character='" + character + '\''
                + ", cost=" + cost +
                '}';
    }
}
