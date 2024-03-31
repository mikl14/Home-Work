package ru.mts.animals;

import org.springframework.stereotype.Component;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public abstract class AbstractAnimal implements Animal, Externalizable {
    protected Random random = new Random();
    protected LocalDate birthDate;
    protected String breed, name, character;
    protected BigDecimal cost;
    protected String secretInformation;


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
        this.secretInformation = InitSecretInformation();
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
        this.secretInformation = InitSecretInformation();
    }

    public AbstractAnimal() {

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
     * Метод <b>InitSecretInformation</b>
     * инициализирует поле secretInformation
     *
     * @return Возвращает случайную строку из файла с секретными данными
     */

    private String InitSecretInformation() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/secretStore/secretInformation.txt"));
            if (!lines.isEmpty()) {
                Random random = new Random();
                int randomIndex = random.nextInt(lines.size());
                String randomLine = lines.get(randomIndex);
                return randomLine;
            } else {
                throw new IllegalArgumentException("Файл секретной информации пуст !");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String getSecretInfomation() {
        return secretInformation;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);
        out.writeUTF(String.valueOf(cost));
        out.writeUTF(String.valueOf(birthDate));
        out.writeUTF(Base64.getEncoder().encodeToString(secretInformation.getBytes()));
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = in.readUTF();
        this.cost = BigDecimal.valueOf(Double.parseDouble(in.readUTF()));
        this.birthDate = LocalDate.parse(in.readUTF());
        this.secretInformation = new String(Base64.getDecoder().decode(in.readUTF()));
    }

    /**
     * Метод <b>getAge</b>
     *
     * @return возраст животного в годах
     */
    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public String getAnimalType() {
        return this.getClass().getSimpleName().toUpperCase(Locale.ROOT);
    }
}
