package ru.mtsbank.fintech.starter_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.animals_creators.AnimalFactory;
import ru.mts.animals_creators.CreateAnimalServiceImpl;
import ru.mtsbank.fintech.starter_tests.test_config.TestsConfiguration;

@ActiveProfiles("test")
@SpringBootTest
@Import(TestsConfiguration.class)
public class AnimalStarterTests {
    @Autowired
    CreateAnimalServiceImpl createAnimalServiceImpl;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(createAnimalServiceImpl);
    }

    /**
     * <b>getAnimalsTest</b>
     * проверяет что без аргументов метод getAnimals() вернул 4 вида животных
     */
    @Test
    void getAnimalsTest() {
        Assertions.assertEquals(createAnimalServiceImpl.getAnimals().size(), 4);
    }

    /**
     * <b>getAnimalsExceptionTest</b>
     * проверяет исключение с отрицательны количеством запрашиваемых животных
     */
    @Test
    void getAnimalsExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> createAnimalServiceImpl.getAnimals(-10));
    }

    /**
     * <b>getAnimalTypeTest</b>
     * Проверяет соответствие типа запрашиваемого животного
     * и возвращенного getAnimal()
     */
    @ParameterizedTest
    @EnumSource(AnimalFactory.AnimalType.class)
    void getAnimalTypeTest(AnimalFactory.AnimalType animalType) {
        createAnimalServiceImpl.setAnimalType(animalType);
        Assertions.assertEquals(animalType.toString(), createAnimalServiceImpl.getAnimal().getAnimalType().toUpperCase());
    }
}
