package ru.mtsbank.fintech;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.mtsbank.fintech.Animals.AbstractAnimal;
import ru.mtsbank.fintech.Animals.Bear;
import ru.mtsbank.fintech.Animals.Cat;
import ru.mtsbank.fintech.Animals.Fish;
import ru.mtsbank.fintech.animalRepository.AnimalRepositoryImpl;
import ru.mtsbank.fintech.animalsCreators.CreateAnimalServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class AnimalRepositoryImplTest {
    private AnimalRepositoryImpl animalRepository;

    /**
     * <b>setUpSearchServiceLmpl</b>
     * - инициализация searchServiceLmpl
     */
    @BeforeEach
    void setUpAnimalRepositoryImpl() {
        animalRepository = new AnimalRepositoryImpl(new CreateAnimalServiceImpl());
    }

    /**
     * <b>findLeapYearNames</b>
     * - Тестирование метода поиска животных рожденных в високосный год
     * Ожидаемый результат: обнаружение 3х животных рожденных в високосный год
     */
    @Test
    void findLeapYearNamesTest() {
        AbstractAnimal[] leapYearsAnimals = new AbstractAnimal[]{
                new Fish("Abis", "Pan", "Evil", LocalDate.of(2012, 12, 1), BigDecimal.valueOf(123), "meat", 12),
                new Bear("White", "Beluga", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "forest", 120),
                new Fish("Not abris", "Not Pan", "Evil", LocalDate.of(2008, 12, 1), BigDecimal.valueOf(123), "meat", 12),
                new Cat("Great", "Piter", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12),
                new Fish("Great", "Piter Pan", "Evil", LocalDate.of(2012, 11, 1), BigDecimal.valueOf(123), "meat", 12),
                new Cat("Abis", "Pan", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12)};

        animalRepository.setAnimalArray(leapYearsAnimals);
        String[] leapYearsAnimalsNames = animalRepository.findLeapYearNames();

        Assertions.assertArrayEquals(leapYearsAnimalsNames, new String[]{"Pan 01-12-2012", "Not Pan 01-12-2008", "Piter Pan 01-11-2012"}); // Ожидается обнаружение 3х животных рожденных в високосный год
    }

    /**
     * <b>findOlderAnimalException</b>
     * - Тестирование метода поиска животных по возрасту
     * Ожидаемый результат: IllegalArgumentException так как передан отрицательный возраст
     */
    @Test
    void findOlderAnimalExceptionTest() {
        AbstractAnimal[] olderYearsAnimals = new AbstractAnimal[]{

                new Fish("Abis", "Pan", "Evil", LocalDate.of(2012, 12, 1), BigDecimal.valueOf(123), "meat", 12),
                new Bear("White", "Beluga", "Evil", LocalDate.of(2000, 12, 1), BigDecimal.valueOf(123), "forest", 120)};
        animalRepository.setAnimalArray(olderYearsAnimals);
        Assertions.assertThrows(IllegalArgumentException.class, () -> animalRepository.findOlderAnimal(-12));
    }

    /**
     * <b>findOlderAnimal</b>
     * - Тестирование метода поиска животных по возрасту
     * Ожидаемый результат: Все найденные значения возраста будут больше искомого
     */
    @ParameterizedTest
    @ValueSource(ints = {7, 8, 9, 10, 11})
    void findOlderAnimalTest(Integer olds) {
        AbstractAnimal[] olderYearsAnimals = new AbstractAnimal[]{
                new Fish("Abis", "Pan", "Evil", LocalDate.now().minusYears(7), BigDecimal.valueOf(123), "meat", 12),
                new Bear("White", "Beluga", "Evil", LocalDate.now().minusYears(8), BigDecimal.valueOf(123), "forest", 120),
                new Fish("Not abris", "Not Pan", "Evil", LocalDate.now().minusYears(9), BigDecimal.valueOf(123), "meat", 12),
                new Cat("Great", "Piter", "Evil", LocalDate.now().minusYears(10), BigDecimal.valueOf(123), "meat", 12),
                new Fish("Great", "Piter Pan", "Evil", LocalDate.now().minusYears(11), BigDecimal.valueOf(123), "meat", 12),
                new Cat("Abis", "Pan", "Evil", LocalDate.now().minusYears(11), BigDecimal.valueOf(123), "meat", 12)};
        animalRepository.setAnimalArray(olderYearsAnimals);
        AbstractAnimal[] olderYearsAnimalsResult = animalRepository.findOlderAnimal(olds);

        for (AbstractAnimal animal : olderYearsAnimalsResult) {
            Assertions.assertTrue(Period.between(animal.getBirthDate(), LocalDate.now()).getYears() > olds); //проверяем что все животные в массиве больше заданного возраста
        }

    }

    /**
     * <b>findDuplicate</b>
     * - Тестирование метода поиска дубликатов в массиве животных
     * Ожидаемый результат: обнаружение 2х дубликатов кота с именем Pan
     */
    @Test
    void findDuplicateTest() {
        AbstractAnimal[] duplicateArray = new AbstractAnimal[]{
                new Cat("Abis", "Pan", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12),
                new Cat("Abis", "Pan", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12),
                new Cat("Not abris", "Not Pan", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12),
                new Cat("Great", "Piter", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12),
                new Cat("Great", "Piter Pan", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12),
                new Cat("Abis", "Pan", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12)};
        animalRepository.setAnimalArray(duplicateArray);
        AbstractAnimal[] duplicateArrayResult = animalRepository.findDuplicate();

        Assertions.assertArrayEquals(duplicateArrayResult, new AbstractAnimal[]{duplicateArray[1], duplicateArray[5]}); // Ожидается обнаружение 2х дубликатов кота с именем Pan
    }
}
