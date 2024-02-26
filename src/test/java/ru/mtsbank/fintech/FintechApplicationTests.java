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
import java.util.HashMap;
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

        System.out.println(leapYearsAnimalsNames);
        Assertions.assertEquals(leapYearsAnimalsNames.toString(), "{FISH Pan=2012-12-01, FISH Piter Pan=2012-11-01, FISH Not Pan=2008-12-01}"); // Ожидается обнаружение 3х животных рожденных в високосный год
    }


    /**
     * <b>findOlderAnimal</b>
     * - Тестирование метода поиска животных по возрасту
     * Ожидаемый результат: Все найденные значения возраста будут больше искомого
     */
    @ParameterizedTest
    @ValueSource(ints = {7, 8, 9, 10, 11})
    void findOlderAnimalTest(Integer olds) {

        Map<String, List<AbstractAnimal>> olderYearsAnimals = new HashMap<String, List<AbstractAnimal>>() {{
            put("FISH", List.of(
                    new Fish("Abis", "Pan", "Evil", LocalDate.now().minusYears(7), BigDecimal.valueOf(123), "meat", 12),
                    new Fish("Not abris", "Not Pan", "Evil", LocalDate.now().minusYears(9), BigDecimal.valueOf(123), "meat", 12),
                    new Fish("Great", "Piter Pan", "Evil", LocalDate.now().minusYears(11), BigDecimal.valueOf(123), "meat", 12)));

            put("CAT", List.of(
                    new Cat("Great", "Piter", "Evil", LocalDate.now().minusYears(10), BigDecimal.valueOf(123), "meat", 12),
                    new Cat("Abis", "Pan", "Evil", LocalDate.now().minusYears(11), BigDecimal.valueOf(123), "meat", 12)));

            put("BEAR", List.of(
                    new Bear("White", "Beluga", "Evil", LocalDate.now().minusYears(8), BigDecimal.valueOf(123), "forest", 120)));
        }};

        animalRepository.setAnimalMap(olderYearsAnimals);
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
        Cat cat = new Cat("Abis", "Pan", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12);

        Assertions.assertEquals(duplicateArrayResult.toString(), "{CAT=2}"); // Ожидается обнаружение 2х дубликатов кота с именем Pan
    }

}
