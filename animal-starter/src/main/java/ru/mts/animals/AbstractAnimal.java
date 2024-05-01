package ru.mts.animals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import ru.mts.exceptions.FileAccessException;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public abstract class AbstractAnimal implements Animal {
    protected Random random = new Random();
    protected LocalDate birthDate;
    protected String breed, name, character;

    protected boolean isWild;
    protected BigDecimal cost;
    @JsonDeserialize(using = Base64Deserializer.class)
    @JsonSerialize(using = Base64Serializer.class)
    protected String secretInformation;

    public static class Base64Serializer extends JsonSerializer<String> {
        @Override
        public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            byte[] encodedBytes = Base64.getEncoder().encode(value.getBytes());
            gen.writeString(new String(encodedBytes));
        }
    }

    public static class Base64Deserializer extends JsonDeserializer<String> {
        @Override
        public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            String base64String = jsonParser.getCodec().readValue(jsonParser, String.class);
            byte[] decodedBytes = Base64.getDecoder().decode(base64String);
            return new String(decodedBytes);
        }
    }

    /**
     * Конструктор AbstractAnimal
     *
     * @param breed             порода животного
     * @param name              имя животного
     * @param birthDate         дата рождения
     * @param character         характер животного
     * @param cost              цена животного
     * @param secretInformation секретная информация которая известна животному
     *
     *                          <p>
     *                          cost округляется до 2х знаков после запятой, для округления использован метод Math.round() т.к в т.з предполагается что в параметре cost храниться сразу округленная цена для магазинов
     */


    public AbstractAnimal(String breed, String name, LocalDate birthDate, String character, BigDecimal cost, String secretInformation) {
        this.breed = breed;
        this.name = name;
        this.birthDate = birthDate;
        this.character = character;
        this.cost = cost.setScale(2, RoundingMode.HALF_UP);
        this.secretInformation = secretInformation;
    }


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
        this.breed = "Number " + (random.nextInt(1000));
        this.name = name;
        this.birthDate = generateRandomDate();
        this.character = character;
        this.cost = (BigDecimal.valueOf(random.nextDouble() * 1000)).setScale(2, RoundingMode.HALF_UP);
        this.secretInformation = InitSecretInformation();
        switch (getAnimalType())
        {
            case "CAT":
            case "FISH":
                this.isWild = false;
                break;
            case "WOLF":
            case "BEAR":
                this.isWild = true;
                break;
        }
    }

    public AbstractAnimal() {
    }

    @JsonProperty("breed")
    public String getBreed() {
        return breed;
    }

    @JsonProperty("breed")
    public void setBreed(String breed) {
        this.breed = breed;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public boolean isWild() {
        return isWild;
    }

    public void setWild(boolean wild) {
        isWild = wild;
    }

    @JsonProperty("cost")
    public BigDecimal getCost() {
        return cost;
    }

    @JsonProperty("cost")
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @JsonProperty("character")
    public String getCharacter() {
        return character;
    }

    @JsonProperty("character")
    public void setCharacter(String character) {
        this.character = character;
    }

    @JsonProperty("birthDate")
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @JsonProperty("birthDate")
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @JsonProperty("secretInformation")
    @JsonDeserialize(using = Base64Deserializer.class)
    @JsonSerialize(using = Base64Serializer.class)
    public String getSecretInfomation() {
        return secretInformation;
    }

    @JsonProperty("secretInformation")
    public void setSecretInformation(String secretInformation) {
        this.secretInformation = secretInformation;
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
     * Метод <b>getAge</b>
     *
     * @return возраст животного в годах
     */
    @JsonIgnore
    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    /**
     * Метод <b>getAnimalType</b>
     *
     * @return тип животного
     */
    @JsonIgnore
    public String getAnimalType() {
        return this.getClass().getSimpleName().toUpperCase(Locale.ROOT);
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
        Resource resource = new ClassPathResource("secretStore/secretInformation.txt");
        try {
            Path path = resource.getFile().toPath();
            List<String> rows = Files.readAllLines(path);
            return rows.get(random.nextInt(rows.size()));
        } catch (IOException e) {
            throw new FileAccessException("Ошибка на этапе доступа к файлу с секретной информацией!" + e);
        }
    }
}
