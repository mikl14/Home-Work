package ru.mtsbank.fintech.starter_tests.test_config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import ru.mts.animals.*;
import ru.mts.animals_creators.AnimalFactory;
import ru.mts.animals_creators.CreateAnimalServiceImpl;
import ru.mtsbank.fintech.animal_repository.AnimalRepositoryImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@TestConfiguration

public class TestsConfiguration {

    @Scope("singleton")
    @Bean
    CreateAnimalServiceImpl createAnimalServiceImpl(AnimalFactory animalFactory) {
        return new CreateAnimalServiceImpl(animalFactory) {
            @Override
            public List<AbstractAnimal> getAnimalsList() {
                return List.of(
                        new Fish("Gold Fish", "Goldie", "neutral", LocalDate.now().minusYears(5), BigDecimal.valueOf(100), "corns", 12, "Look Behind You!"),
                        new Fish("Guppy", "Tiny", "Evil", LocalDate.now().minusYears(7), BigDecimal.valueOf(200), "corns", 12, "I sad look behind you!"),
                        new Fish("Magic Karp", "Slark", "Evil", LocalDate.now().minusYears(9), BigDecimal.valueOf(250), "meat", 12, "I'm Alien"),
                        new Fish("Shark", "Blue Dragon", "Good", LocalDate.now().minusYears(11), BigDecimal.valueOf(550), "meat", 12, "I work in MIB"),
                        new Cat("Persian", "Kitty", "Evil", LocalDate.now().minusYears(10), BigDecimal.valueOf(330), "meat", 12, "has no money"),
                        new Cat("CyberCat", "V", "101010", LocalDate.now().minusYears(2), BigDecimal.valueOf(256), "electric", 12, "small dog -- Broken Data"),
                        new Cat("Tibet", "Cloud", "Evil", LocalDate.now().minusYears(4), BigDecimal.valueOf(300), "meat", 12, "Honey!"),
                        new Cat("Stray", "Akira", "Good", LocalDate.now().minusYears(6), BigDecimal.valueOf(125), "meat", 12, "Check out!"),
                        new Bear("Animatronic", "GoldenFreddy", "Very Bad", LocalDate.now().minusYears(6), BigDecimal.valueOf(600), "Fredy's Pizza", 120, "Bite 87"),
                        new Bear("White", "Beluga", "Hungry", LocalDate.now().minusYears(3), BigDecimal.valueOf(500), "Arctic", 120, "I'm not white"),
                        new Bear("Brown", "Ivan", "True Patriotic", LocalDate.now().minusYears(5), BigDecimal.valueOf(500), "Russian taiga", 120, "KGB agent"),
                        new Bear("White", "Beluga", "Hungry", LocalDate.now().minusYears(3), BigDecimal.valueOf(500), "Arctic", 120, "I'm white"),
                        new Wolf("Grey", "Ball", "Very Bad", LocalDate.now().minusYears(5), BigDecimal.valueOf(300), "forest", 70, "I'm eat red hat"),
                        new Wolf("HomeWolf", "Balloon", "Pretty", LocalDate.now().minusYears(3), BigDecimal.valueOf(450), "home", 70, "he is small"),
                        new Wolf("Dingo", "Red", "Evil", LocalDate.now().minusYears(8), BigDecimal.valueOf(200), "desert", 70, "secretInfo"),
                        new Wolf("Dingo", "Red", "Evil", LocalDate.now().minusYears(8), BigDecimal.valueOf(200), "desert", 70, "secretInfo")
                );
            }
        };
    }

}