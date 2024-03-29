package ru.mts.animals;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

@Component
public abstract class AbstractAnimal implements Animal {
    protected Random random = new Random();
    protected LocalDate birthDate;
    protected String breed, name, character;
    protected BigDecimal cost;

    /**
     * Конструктор AbstractAnimal
     *
     * @param breed     порода животного
     * @param name      имя животного
     * @param birthDate дата рождения
     * @param character характер животного
     * @param cost      цена животного
     *                  <p>
     *                  cost округляется до 2х знаков после запятой, для округления использован метод Math.round() т.к в т.з предполагается что в параметре cost храниться сразу округленная цена для магазинов
     */
    public AbstractAnimal(String breed, String name, LocalDate birthDate, String character, BigDecimal cost) {
        this.breed = breed;
        this.name = name;
        this.birthDate = birthDate;
        this.character = character;
        this.cost = cost.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Конструктор AbstractAnimal - в этой реализации остальные параметры будут сгенерированы методами
     *
     * @param character характер животного
     * @see #generateRandomDate()
     */
    public AbstractAnimal(String name, String character) {
        this.breed = "Порода №" + (random.nextInt(1000));
        this.name = name;
        this.birthDate = generateRandomDate();
        this.character = character;
        this.cost = (BigDecimal.valueOf(random.nextDouble() * 1000)).setScale(2, RoundingMode.HALF_UP);
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
    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public String getCharacter() {
        return character;
    }

    @Override
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * <b>getFormatDate</b>
     *
     * @param format формат строки
     * @return дату в формате format
     */
    public String getFormatDate(String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(birthDate);
    }

    /**
     * <b>equals</b>
     *
     * @param obj формат строки
     * @return true или false в зависимости от равенства объектов
     */
    @Override
    public boolean equals(Object obj) { // будут равны если равны имена, даты рождения и порода

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        var animalObj = ((AbstractAnimal) obj);

        return Objects.equals(name, animalObj.name)
                && Objects.equals(birthDate, animalObj.birthDate)
                && Objects.equals(breed, animalObj.breed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthDate, breed);
    }

    /**
     * Метод <b>generateRandomDate</b>
     * возвращает случайную дату
     *
     * @return LocalDate
     */
    private LocalDate generateRandomDate() {
        long epoch = (long) (random.nextDouble() * (LocalDate.now().toEpochDay()));
        return LocalDate.ofEpochDay(epoch);
    }

    /**
     * Метод <b>getAge</b>
     * @return возраст животного в годах
     */
    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public String getAnimalType()
    {
        return this.getClass().getSimpleName().toUpperCase(Locale.ROOT);
    }
}
