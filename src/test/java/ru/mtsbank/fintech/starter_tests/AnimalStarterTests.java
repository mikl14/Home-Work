package ru.mtsbank.fintech.starter_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.animals.Bear;
import ru.mts.animals.Fish;
import ru.mts.animals_creators.AnimalFactory;
import ru.mts.animals_creators.CreateAnimalServiceImpl;
import ru.mtsbank.fintech.starter_tests.test_config.TestsConfiguration;

import java.math.BigDecimal;
import java.time.LocalDate;

@ActiveProfiles("test")
@SpringBootTest

@Import(TestsConfiguration.class)

public class AnimalStarterTests {
    @Autowired
    CreateAnimalServiceImpl createAnimalServiceImpl;
    @InjectMocks
    CreateAnimalServiceImpl mockedCreateAnimalServiceImpl;

    @Mock
    AnimalFactory animalFactory;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(createAnimalServiceImpl);
    }

    @Test
    void getAnimalsTest() {
        Assertions.assertEquals(createAnimalServiceImpl.getAnimals().length, 10);
    }

    @Test
    void getAnimalTest() {
        createAnimalServiceImpl.setAnimalType(AnimalFactory.AnimalType.BEAR);
        Assertions.assertEquals(Bear.class, createAnimalServiceImpl.getAnimal().getClass());

    }

    @Test
    void getAnimalsExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> createAnimalServiceImpl.getAnimals(-10));
    }

    @Test
    void getAnimalExceptionTest() {
        Mockito.when(animalFactory.getAnimal(AnimalFactory.AnimalType.CAT)).thenReturn(new Fish("Abis", "Pan", "Evil", LocalDate.of(2012, 12, 1), BigDecimal.valueOf(123), "meat", 12));
        mockedCreateAnimalServiceImpl.setAnimalType(AnimalFactory.AnimalType.CAT);
        Assertions.assertThrows(IllegalStateException.class, () -> mockedCreateAnimalServiceImpl.getAnimal());

    }

}
