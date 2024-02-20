package ru.mtsbank.fintech;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.animals.AbstractAnimal;
import ru.mts.animals.Bear;
import ru.mts.animals.Cat;
import ru.mts.animals.Fish;
import ru.mtsbank.fintech.animal_repository.AnimalRepositoryImpl;
import ru.mtsbank.fintech.starter_tests.test_config.TestsConfiguration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@ActiveProfiles("test")
@SpringBootTest
@Import(TestsConfiguration.class)
class FintechApplicationTests {
    @Autowired
    AnimalRepositoryImpl animalRepository;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(animalRepository);
    }

    /**
     * <b>animalRepositoryArrayTest</b>
     * проверяет что после инициализации массив оказался заполнен
     */
    @Test
    void animalRepositoryArrayTest() {
        Assertions.assertNotEquals(animalRepository.getAnimalArray().length, 0);
    }

    /**
     * <b>findOlderAnimalExceptionTest</b>
     * - Тестирование метода поиска животных по возрасту
     * Ожидаемый результат: IllegalArgumentException так как передан отрицательный возраст
     */
    @Test
    void findOlderAnimalExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> animalRepository.findOlderAnimal(-12));
    }


    /**
     * <b>findLeapYearNames</b>
     * - Тестирование метода поиска животных рожденных в високосный год
     * Ожидаемый результат: обнаружение 3х животных рожденных в високосный год
     */
    @Test
    void findLeapYearNamesTest() {
        String[] leapYearsAnimalsNames = animalRepository.findLeapYearNames();
        Assertions.assertArrayEquals(leapYearsAnimalsNames, new String[]{"Pan 01-12-2012", "Not Pan 01-12-2008", "Piter Pan 01-11-2012"}); // Ожидается обнаружение 3х животных рожденных в високосный год
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
        AbstractAnimal[] duplicateArrayResult = animalRepository.findDuplicate();
        Cat cat = new Cat("Abis", "Pan", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12);

        Assertions.assertArrayEquals(duplicateArrayResult, new AbstractAnimal[]{cat, cat}); // Ожидается обнаружение 2х дубликатов кота с именем Pan
    }

}
