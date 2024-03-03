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
import ru.mtsbank.fintech.animal_repository.AnimalRepositoryImpl;
import ru.mtsbank.fintech.starter_tests.test_config.TestsConfiguration;

import java.time.LocalDate;
import java.time.Period;
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
        Map<String, Integer> duplicateArrayResult = animalRepository.findDuplicate();
        Assertions.assertEquals(2, duplicateArrayResult.get("CAT")); // Ожидается обнаружение 2х дубликатов кота с именем Pan
    }

}