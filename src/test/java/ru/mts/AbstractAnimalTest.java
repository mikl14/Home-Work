package ru.mts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mts.Animals.AbstractAnimal;
import ru.mts.Animals.Cat;
import ru.mts.Animals.Fish;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AbstractAnimalTest {

    private AbstractAnimal animal;

    /**
     * <b>setUpAnimal</b>
     * <p>
     * - инициализация животного для сравнения
     */
    @BeforeEach
    void setUpAnimal() {
        animal = new Cat("Abis", "Pan", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12);
    }

    /**
     * <b>equalsFalse</b> -
     * <p>
     * Тест проверяющий случаи когда equals должен возвращать false
     * Когда у животных разные имена или дата рождения, а так же породы
     * Когда у животных разные типы
     */
    @Test
    void equalsFalseTest() {
        boolean res = animal.equals(new Cat("Abi", "Pan", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12));
        Assertions.assertFalse(res);

        res = animal.equals(new Cat("Abis", "Peter", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12));
        Assertions.assertFalse(res);

        res = animal.equals(new Cat("Abis", "Pan", "Evil", LocalDate.of(2021, 12, 1), BigDecimal.valueOf(123), "meat", 12));
        Assertions.assertFalse(res);

        res = animal.equals(new Fish("Abis", "Pan", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12));
        Assertions.assertFalse(res);
    }

    /**
     * <b>equalsTrue</b> -
     * Тест проверяющий случаи когда equals должен возвращать true
     * Когда у животных одного типа одинаковые имена, породы и даты рождения
     * Когда у животных одного типа одинаковые имена, породы и даты рождения, но разный характер
     */
    @Test
    void equalsTrueTest() {
        boolean res = animal.equals(new Cat("Abis", "Pan", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12));
        Assertions.assertTrue(res);

        res = animal.equals(new Cat("Abis", "Pan", "Good", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12));
        Assertions.assertTrue(res);
    }

}
