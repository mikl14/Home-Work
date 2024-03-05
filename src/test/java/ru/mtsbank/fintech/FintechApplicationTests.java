package ru.mtsbank.fintech;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.animals.AbstractAnimal;
import ru.mts.animals.Cat;
import ru.mts.animals.Fish;
import ru.mtsbank.fintech.animal_repository.AnimalRepositoryImpl;
import ru.mtsbank.fintech.starter_tests.test_config.TestsConfiguration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;

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
        Assertions.assertNotEquals(animalRepository.getAnimalArray().size(), 0);
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
        Map<String, LocalDate> leapYearsAnimalsNames = animalRepository.findLeapYearNames();

        for (Map.Entry<String, LocalDate> animal : leapYearsAnimalsNames.entrySet()) {
            Assertions.assertTrue(animal.getValue().isLeapYear()); // Ожидается что все возвращенные животные рождены в високосный год
        }

    }


    /**
     * <b>findOlderAnimal</b>
     * - Тестирование метода поиска животных по возрасту
     * Ожидаемый результат: Все найденные значения возраста будут больше искомого
     */
    @ParameterizedTest
    @ValueSource(ints = {7, 8, 9, 10, 11})
    void findOlderAnimalTest(Integer olds) {

        Map<AbstractAnimal, Integer> olderYearsAnimalsResult = animalRepository.findOlderAnimal(olds);

        for (Map.Entry<AbstractAnimal, Integer> animal : olderYearsAnimalsResult.entrySet()) {
            Assertions.assertTrue(Period.between(LocalDate.of(animal.getValue(), 1, 1), LocalDate.now()).getYears() > olds); //проверяем что все животные в массиве больше заданного возраста
        }

    }

    /**
     * <b>findDuplicate</b>
     * - Тестирование метода поиска дубликатов в массиве животных
     * Ожидаемый результат: обнаружение 2х дубликатов кота
     */
    @Test
    void findDuplicateTest() {
        Map<String, List<AbstractAnimal>> duplicateArrayResult = animalRepository.findDuplicate();
        Assertions.assertEquals(List.of(
                        new Cat("Abis", "Pan", "Evil", LocalDate.now().minusYears(10), BigDecimal.valueOf(123), "meat", 12),
                        new Cat("Abis", "Pan", "Evil", LocalDate.now().minusYears(10), BigDecimal.valueOf(123), "meat", 12),
                        new Cat("Abis", "Pan", "Good", LocalDate.now().minusYears(10), BigDecimal.valueOf(123), "meat", 12)),
                duplicateArrayResult.get("CAT")); // Ожидается обнаружение 2х дубликатов кота с именем Pan
    }

    /**
     * <b>findAverageAgeTest</b>
     * - Тестирование метода поиска среднего возраста животных
     */
    @Test
    void findAverageAgeTest() {

        Assertions.assertEquals((double) (10 + 13 + 15) / 3, animalRepository.findAverageAge(List.of(
                new Cat("Abis", "Pan", "Evil", LocalDate.now().minusYears(10), BigDecimal.valueOf(123), "meat", 12),
                new Cat("Abis", "Pan", "Evil", LocalDate.now().minusYears(13), BigDecimal.valueOf(123), "meat", 12),
                new Cat("Abis", "Pan", "Good", LocalDate.now().minusYears(15), BigDecimal.valueOf(123), "meat", 12)))); // Ожидается обнаружение 2х дубликатов кота с именем Pan
    }

    /**
     * <b>findOldAndExpensiveTest</b>
     * - Тестирование метода поиска животных старше 5 лет и с ценой выше среднего
     */
    @Test
    void findOldAndExpensiveTest() {

        List<AbstractAnimal> animalList = List.of(
                new Fish("Abis", "fishPan", "Good", LocalDate.now().minusYears(15), BigDecimal.valueOf(20), "meat", 12),
                new Cat("Abis", "Pan", "Evil", LocalDate.now().minusYears(10), BigDecimal.valueOf(165), "meat", 12),
                new Cat("Abis", "Pan", "Evil", LocalDate.now().minusYears(13), BigDecimal.valueOf(123), "meat", 12),
                new Fish("Abis", "fishPan", "Evil", LocalDate.now().minusYears(2), BigDecimal.valueOf(123), "meat", 12),
                new Fish("Abis", "fishPan", "Evil", LocalDate.now().minusYears(3), BigDecimal.valueOf(167), "meat", 12),
                new Cat("Abis", "Pan", "Good", LocalDate.now().minusYears(5), BigDecimal.valueOf(1), "meat", 12)
        );

        List<AbstractAnimal> expectedAnimalList = List.of(
                new Cat("Abis", "Pan", "Evil", LocalDate.now().minusYears(10), BigDecimal.valueOf(165), "meat", 12),
                new Cat("Abis", "Pan", "Evil", LocalDate.now().minusYears(13), BigDecimal.valueOf(123), "meat", 12)
        );

        Assertions.assertEquals(expectedAnimalList, animalRepository.findOldAndExpensive(5, animalList)); // Ожидается обнаружение 2х дубликатов кота с именем Pan
    }

    /**
     * <b>findOldAndExpensiveExceptionTest</b>
     * - Тестирование метода поиска животных старше 5 лет и с ценой выше среднего
     * Ожидаемый результат: IllegalArgumentException так как передан отрицательный возраст
     */
    @Test
    void findOldAndExpensiveExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> animalRepository.findOldAndExpensive(-12, Mockito.anyList()));
    }

    /**
     * <b>findMinConstAnimalsTest</b>
     * - Тестирование метода поиска 3-х животных с самой низкой ценой
     */
    @Test
    void findMinConstAnimalsTest() {

        List<AbstractAnimal> animalList = List.of(
                new Fish("AA", "AA", "Good", LocalDate.now().minusYears(15), BigDecimal.valueOf(20), "meat", 12),
                new Cat("AB", "AB", "Evil", LocalDate.now().minusYears(10), BigDecimal.valueOf(165), "meat", 12),
                new Cat("B", "B", "Evil", LocalDate.now().minusYears(13), BigDecimal.valueOf(123), "meat", 12),
                new Fish("C", "C", "Evil", LocalDate.now().minusYears(2), BigDecimal.valueOf(1000), "meat", 12)
        );

        List<String> expectedAnimalList = List.of("C", "B", "AB");

        Assertions.assertEquals(expectedAnimalList, animalRepository.findMinConstAnimals(animalList)); // Ожидается обнаружение 2х дубликатов кота с именем Pan
    }


}
