package ru.mts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.mts.Animals.AbstractAnimal;
import ru.mts.Animals.Bear;
import ru.mts.Animals.Cat;
import ru.mts.Animals.Fish;
import ru.mts.animalSearch.SearchServiceLmpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class SearchServiceLmplTest {
    private SearchServiceLmpl searchServiceLmpl;

    /**
     * <b>setUpSearchServiceLmpl</b>
     * - инициализация searchServiceLmpl
     */
    @BeforeEach
    void setUpSearchServiceLmpl() {
        searchServiceLmpl = new SearchServiceLmpl();
    }
    /**
     * <b>findLeapYearNames</b>
     * - Тестирование метода поиска животных рожденных в високосный год
     * Ожидаемый результат: обнаружение 3х животных рожденных в високосный год
     */
    @Test
    void findLeapYearNames() {
        AbstractAnimal[] leapYearsAnimals = new AbstractAnimal[]{
                new Fish("Abis", "Pan", "Evil", LocalDate.of(2012, 12, 1), BigDecimal.valueOf(123), "meat", 12),
                new Bear("White", "Beluga", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "forest", 120),
                new Fish("Not abris", "Not Pan", "Evil", LocalDate.of(2008, 12, 1), BigDecimal.valueOf(123), "meat", 12),
                new Cat("Great", "Piter", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12),
                new Fish("Great", "Piter Pan", "Evil", LocalDate.of(2012, 11, 1), BigDecimal.valueOf(123), "meat", 12),
                new Cat("Abis", "Pan", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12)};

        String[] leapYearsAnimalsNames = searchServiceLmpl.findLeapYearNames(leapYearsAnimals);

        Assertions.assertArrayEquals(leapYearsAnimalsNames,new String[]{"Pan 01-12-2012","Not Pan 01-12-2008","Piter Pan 01-11-2012"}); // Ожидается обнаружение 3х животных рожденных в високосный год
    }
    /**
     * <b>findOlderAnimalException</b>
     * - Тестирование метода поиска животных по возрасту
     * Ожидаемый результат: ArithmeticException так как передан отрицательный возраст
     */
    @Test
    void findOlderAnimalException() {
        AbstractAnimal[] olderYearsAnimals = new AbstractAnimal[]{
                new Fish("Abis", "Pan", "Evil", LocalDate.of(2012, 12, 1), BigDecimal.valueOf(123), "meat", 12),
                new Bear("White", "Beluga", "Evil", LocalDate.of(2000, 12, 1), BigDecimal.valueOf(123), "forest", 120)};

                Assertions.assertThrows(ArithmeticException.class,() -> searchServiceLmpl.findOlderAnimal(olderYearsAnimals, -12));
    }

    /**
     * <b>findOlderAnimal</b>
     * - Тестирование метода поиска животных по возрасту
     * Ожидаемый результат: Все найденные значения возраста будут соответствовать искомому
     */
    @ParameterizedTest
    @ValueSource(ints = {3,4,5,6,7,8,9,10,11,12,15,20})
    void findOlderAnimal(Integer olds){
        AbstractAnimal[] olderYearsAnimals = new AbstractAnimal[]{
                new Fish("Abis", "Pan", "Evil", LocalDate.of(2012, 12, 1), BigDecimal.valueOf(123), "meat", 12),
                new Bear("White", "Beluga", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "forest", 120),
                new Fish("Not abris", "Not Pan", "Evil", LocalDate.of(2008, 12, 1), BigDecimal.valueOf(123), "meat", 12),
                new Cat("Great", "Piter", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12),
                new Fish("Great", "Piter Pan", "Evil", LocalDate.of(2012, 11, 1), BigDecimal.valueOf(123), "meat", 12),
                new Cat("Abis", "Pan", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12)};

        AbstractAnimal[] olderYearsAnimalsResult = searchServiceLmpl.findOlderAnimal(olderYearsAnimals, olds);

        for (AbstractAnimal animal:olderYearsAnimalsResult) {
            Assertions.assertEquals(Period.between(animal.getBirthDate(), LocalDate.now()).getYears(),olds);
        }

    }
    /**
     * <b>findDuplicate</b>
     * - Тестирование метода поиска дубликатов в массиве животных
     * Ожидаемый результат: обнаружение 2х дубликатов кота с именем Pan
     */
    @Test
    void findDuplicate() {
        AbstractAnimal[] duplicateArray = new AbstractAnimal[]{
                new Cat("Abis", "Pan", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12),
                new Cat("Abis", "Pan", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12),
                new Cat("Not abris", "Not Pan", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12),
                new Cat("Great", "Piter", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12),
                new Cat("Great", "Piter Pan", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12),
                new Cat("Abis", "Pan", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12)};

        AbstractAnimal[] duplicateArrayResult = searchServiceLmpl.findDuplicate(duplicateArray);

        Assertions.assertArrayEquals(duplicateArrayResult,new AbstractAnimal[]{duplicateArray[1],duplicateArray[5]}); // Ожидается обнаружение 2х дубликатов кота с именем Pan
    }
}
