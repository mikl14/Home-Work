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
import ru.mts.animals.*;
import ru.mtsbank.fintech.animal_repository.AnimalRepositoryImpl;
import ru.mtsbank.fintech.exceptions.IllegalListSizeException;
import ru.mtsbank.fintech.exceptions.IllegalValueException;
import ru.mtsbank.fintech.starter_tests.test_config.TestsConfiguration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        Assertions.assertThrows(IllegalValueException.class, () -> animalRepository.findOlderAnimal(-12));
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
     * Ожидаемый результат: Все найденные значения возраста будут больше искомого, а в случае если животных старше искомого нет будет возвращено самое старое животное
     */
    @ParameterizedTest
    @ValueSource(ints = {7, 8, 9, 10, 11})
    void findOlderAnimalTest(Integer olds) {
        Map<AbstractAnimal, Integer> olderYearsAnimalsResult = animalRepository.findOlderAnimal(olds);

        AbstractAnimal theOlderAnimal = animalRepository.getAnimalArray().values().stream()
                .flatMap(List::stream)
                .max(Comparator.comparingInt(AbstractAnimal::getAge)).orElse(null); //находим самое старое животное

        for (Map.Entry<AbstractAnimal, Integer> animal : olderYearsAnimalsResult.entrySet()) {

            if (animal.getKey().equals(theOlderAnimal) && olds >= animal.getKey().getAge()) //если было возвращено самое старое животное и при этом заданный возраст больше или равен его возрасту
            {
                Assertions.assertTrue(true);
            } else {
                Assertions.assertTrue(animal.getKey().getAge() > olds); //проверяем что все животные в массиве больше заданного возраста
            }
        }
    }

    /**
     * <b>findOlderAnimalContainsValuesTest</b>
     * - Тестирование метода поиска животных по возрасту
     * Ожидаемый результат: во всех случаях итог будет содержать всех животных за исключением дубликатов
     */
    @Test
    void findOlderAnimalContainsValuesTest() {
        AbstractAnimal theYoungestAnimal = animalRepository.getAnimalArray().values().stream()
                .flatMap(List::stream)
                .min(Comparator.comparingInt(AbstractAnimal::getAge)).orElse(null); //находим самое молодое животное

        List<AbstractAnimal> actualOlderYearsAnimalsList = new ArrayList<>();

        assert theYoungestAnimal != null;
        Map<AbstractAnimal, Integer> olderYearsAnimalsResult = animalRepository.findOlderAnimal(theYoungestAnimal.getAge() - 1); //передаем возраст молодого животного - 1, чтобы получить всех

        Map<String, List<AbstractAnimal>> allAnimalMap = animalRepository.getAnimalArray();

        for (Map.Entry<String, List<AbstractAnimal>> entry : allAnimalMap.entrySet()) {
            actualOlderYearsAnimalsList.addAll(entry.getValue().stream().distinct().collect(Collectors.toList()));
        }
        Assertions.assertEquals(olderYearsAnimalsResult.size(), actualOlderYearsAnimalsList.size());
    }

    /**
     * <b>findOlderAnimalOlderAnimalTest</b>
     * - Тестирование метода поиска животных по возрасту, должно быть возвращено самое старое животное или одно из самых старых
     * Ожидаемый результат: в вернувшемся списке будет только 1 животное
     */
    @Test
    void findOlderAnimalOlderAnimalSizeTest() {
        AbstractAnimal theOlderAnimal = animalRepository.getAnimalArray().values().stream()
                .flatMap(List::stream)
                .max(Comparator.comparingInt(AbstractAnimal::getAge)).orElse(null); //находим самое старое животное

        assert theOlderAnimal != null;
        Map<AbstractAnimal, Integer> olderYearsAnimalsResult = animalRepository.findOlderAnimal(theOlderAnimal.getAge() + 1);

        Assertions.assertEquals(olderYearsAnimalsResult.size(), 1);
    }

    /**
     * <b>findDuplicate</b>
     * - Тестирование метода поиска дубликатов в массиве животных
     * животные считаются одинаковыми если совпадают породы, даты рождения и имена
     * Ожидаемый результат: обнаружение 2х дубликатов волка с именем Red и 2х медведей с именем Beluga
     */
    @Test
    void findDuplicateTest() {
        Map<String, List<AbstractAnimal>> duplicateArrayResult = animalRepository.findDuplicate();

        List<AbstractAnimal> expectedList = List.of(
                new Wolf("Dingo", "Red", "Evil", LocalDate.now().minusYears(8), BigDecimal.valueOf(123), "desert", 70),
                new Wolf("Dingo", "Red", "Evil", LocalDate.now().minusYears(8), BigDecimal.valueOf(123), "desert", 70),
                new Bear("White", "Beluga", "Hungry", LocalDate.now().minusYears(3), BigDecimal.valueOf(123), "Arctic", 120),
                new Bear("White", "Beluga", "Hungry", LocalDate.now().minusYears(3), BigDecimal.valueOf(123), "Arctic", 120));

        List<AbstractAnimal> actualList = new ArrayList<AbstractAnimal>();
        for (Map.Entry<String, List<AbstractAnimal>> entry : duplicateArrayResult.entrySet()) {
            actualList.addAll(entry.getValue());
        }
        Assertions.assertEquals(expectedList, actualList); // Ожидается обнаружение обнаружение 2х дубликатов волка с именем Red и 2х медведей с именем Beluga
    }

    /**
     * <b>findAverageAgeTest</b>
     * - Тестирование метода поиска среднего возраста животных
     * Ожидаемый результат: средний возраст животных из переданного списка
     */
    @Test
    void findAverageAgeTest() {
        List<AbstractAnimal> animalList = animalRepository.getAnimalArray().get("CAT"); //Берем список кошек
        double expectedAvg = 5.5; // В списке кошек 4 кошки возрастом 10,4,2,6 лет
        Assertions.assertEquals(expectedAvg, animalRepository.findAverageAge(animalList));
    }

    /**
     * <b>findAverageAgeExceptionTest</b>
     * - Тестирование исключения метода поиска среднего возраста животных
     * Ожидаемый результат: исключение IllegalValueException так как передан пустой список
     */
    @Test
    void findAverageAgeExceptionTest() {
        Assertions.assertThrows(IllegalValueException.class, () -> animalRepository.findAverageAge(Mockito.anyList()));
    }

    /**
     * <b>findOldAndExpensiveTest</b>
     * - Тестирование метода поиска животных старше 5 лет и с ценой выше среднего
     * Ожидаемый результат: список животных с животными старше переданного возраста и ценой выше средней
     */
    @Test
    void findOldAndExpensiveTest() {
        List<AbstractAnimal> animalList = new ArrayList<>(animalRepository.getAnimalArray().get("FISH"));
        animalList.addAll(animalRepository.getAnimalArray().get("BEAR")); //передаваемый список составленный из рыб и медведей из animalRepository

        List<AbstractAnimal> expectedAnimalList = List.of(
                new Fish("Shark", "Blue Dragon", "Good", LocalDate.now().minusYears(11), BigDecimal.valueOf(550), "meat", 12),
                new Bear("Animatronic", "GoldenFreddy", "Very Bad", LocalDate.now().minusYears(6), BigDecimal.valueOf(600), "Fredy's Pizza", 120)
        );
        //ожидаемый результат
        try {
            Assertions.assertEquals(expectedAnimalList, animalRepository.findOldAndExpensive(5, animalList));
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    /**
     * <b>findOldAndExpensiveAgeExceptionTest</b>
     * - Тестирование метода поиска животных старше age лет и с ценой выше среднего
     * Ожидаемый результат: IllegalValueException так как передан отрицательный возраст
     */
    @Test
    void findOldAndExpensiveAgeExceptionTest() {
        Assertions.assertThrows(IllegalValueException.class, () -> animalRepository.findOldAndExpensive(-12, Mockito.anyList()));
    }

    /**
     * <b>findOldAndExpensiveEmptyListExceptionTest</b>
     * - Тестирование метода поиска животных старше age лет и с ценой выше среднего
     * Ожидаемый результат: IllegalListSizeException так как передан пустой список
     */
    @Test
    void findOldAndExpensiveEmptyListExceptionTest() {
        Assertions.assertThrows(IllegalListSizeException.class, () -> animalRepository.findOldAndExpensive(2, Mockito.anyList()));
    }

    /**
     * <b>findMinConstAnimalsTest</b>
     * - Тестирование метода поиска животных с самой низкой ценой
     * Ожидаемый результат: 3 самых дешевых животных в обратном алфавитном порядке
     */
    @Test
    void findMinConstAnimalsTest() {
        List<AbstractAnimal> animalList = List.of(
                new Cat("Persian", "Kitty", "Evil", LocalDate.now().minusYears(10), BigDecimal.valueOf(330), "meat", 12),
                new Cat("CyberCat", "V", "101010", LocalDate.now().minusYears(2), BigDecimal.valueOf(256), "electric", 12),
                new Cat("Tibet", "Cloud", "Evil", LocalDate.now().minusYears(4), BigDecimal.valueOf(300), "meat", 12),
                new Cat("Stray", "Akira", "Good", LocalDate.now().minusYears(6), BigDecimal.valueOf(125), "meat", 12));

        List<String> expectedAnimalList = List.of("V", "Cloud", "Akira"); //Akira - 125,Cloud - 300, V - 256

        try {
            Assertions.assertEquals(expectedAnimalList, animalRepository.findMinConstAnimals(animalList, 3));
        } catch (IllegalListSizeException e) { // если вернулось исключение, то fail
            Assertions.fail();
        }
    }

    /**
     * <b>findMinConstAnimalsSizeTest</b>
     * - Тестирование метода поиска животных с самой низкой ценой
     * Ожидаемый результат: размер возвращаемого списка всегда равен заданному limit
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void findMinConstAnimalsSizeTest(int limit) {
        List<AbstractAnimal> animalList = List.of(
                new Cat("Persian", "Kitty", "Evil", LocalDate.now().minusYears(10), BigDecimal.valueOf(330), "meat", 12),
                new Cat("CyberCat", "V", "101010", LocalDate.now().minusYears(2), BigDecimal.valueOf(256), "electric", 12),
                new Cat("Tibet", "Cloud", "Evil", LocalDate.now().minusYears(4), BigDecimal.valueOf(300), "meat", 12),
                new Cat("Stray", "Akira", "Good", LocalDate.now().minusYears(6), BigDecimal.valueOf(125), "meat", 12));
        try {
            Assertions.assertEquals(limit, animalRepository.findMinConstAnimals(animalList, limit).size()); // если limit меньше или равен длине списка должен вернуться список длинной limits
        } catch (Exception e) {
            Assertions.fail(); //любое исключение вызовет fail
        }
    }

    /**
     * <b>findMinConstAnimalsSizeExceptionTest</b>
     * - Тестирование метода поиска животных с самой низкой ценой
     * Ожидаемый результат: исключение IllegalListSizeException т.к. limit больше длинны списка
     */
    @ParameterizedTest
    @ValueSource(ints = {5, 6, 7, 8, 9, 10})
    void findMinConstAnimalsSizeExceptionTest(int limit) {
        List<AbstractAnimal> animalList = List.of(
                new Cat("Persian", "Kitty", "Evil", LocalDate.now().minusYears(10), BigDecimal.valueOf(330), "meat", 12),
                new Cat("CyberCat", "V", "101010", LocalDate.now().minusYears(2), BigDecimal.valueOf(256), "electric", 12),
                new Cat("Tibet", "Cloud", "Evil", LocalDate.now().minusYears(4), BigDecimal.valueOf(300), "meat", 12),
                new Cat("Stray", "Akira", "Good", LocalDate.now().minusYears(6), BigDecimal.valueOf(125), "meat", 12));

        Assertions.assertThrows(IllegalListSizeException.class, () -> animalRepository.findMinConstAnimals(animalList, limit)); // если limit больше длинны списка, то ожидается исключение
    }

}
