package ru.mtsbank.fintech.starter_tests.test_config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import ru.mts.animals.AbstractAnimal;
import ru.mts.animals_creators.AnimalFactory;
import ru.mts.animals_creators.CreateAnimalServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@TestConfiguration

public class TestsConfiguration {

    @Scope("singleton")
    @Bean
    CreateAnimalServiceImpl createAnimalServiceImpl(AnimalFactory animalFactory) {
        return new CreateAnimalServiceImpl(animalFactory) {
            @Override
            public List<AbstractAnimal> getAnimalsList() {
                return List.of(
                        new AbstractAnimal("FISH", "Gold Fish", "Goldie", LocalDate.now().minusYears(5), "corns", BigDecimal.valueOf(100), "Look Behind You!"),
                        new AbstractAnimal("FISH", "Guppy", "Tiny", LocalDate.now().minusYears(7), "Evil", BigDecimal.valueOf(200), "I sad look behind you!"),
                        new AbstractAnimal("FISH", "Magic Karp", "Slark", LocalDate.now().minusYears(9), "Evil", BigDecimal.valueOf(250), "I'm Alien"),
                        new AbstractAnimal("FISH", "Shark", "Blue Dragon", LocalDate.now().minusYears(11), "Good", BigDecimal.valueOf(550), "I work in MIB"),
                        new AbstractAnimal("CAT", "Persian", "Kitty", LocalDate.now().minusYears(10), "Evil", BigDecimal.valueOf(330), "has no money"),
                        new AbstractAnimal("CAT", "CyberCat", "V", LocalDate.now().minusYears(2), "101010", BigDecimal.valueOf(256), "small dog -- Broken Data"),
                        new AbstractAnimal("CAT", "Tibet", "Cloud", LocalDate.now().minusYears(4), "Evil", BigDecimal.valueOf(300), "Honey!"),
                        new AbstractAnimal("CAT", "Stray", "Akira", LocalDate.now().minusYears(6), "Good", BigDecimal.valueOf(125), "Check out!"),
                        new AbstractAnimal("BEAR", "Animatronic", "GoldenFreddy", LocalDate.now().minusYears(6), "Very Bad", BigDecimal.valueOf(600), "Bite 87"),
                        new AbstractAnimal("BEAR", "White", "Beluga", LocalDate.now().minusYears(3), "Hungry", BigDecimal.valueOf(500), "I'm not white"),
                        new AbstractAnimal("BEAR", "Brown", "Ivan", LocalDate.now().minusYears(5), "True Patriotic", BigDecimal.valueOf(500), "KGB agent"),
                        new AbstractAnimal("BEAR", "White", "Beluga", LocalDate.now().minusYears(3), "Hungry", BigDecimal.valueOf(500), "I'm white"),
                        new AbstractAnimal("WOLF", "Grey", "Ball", LocalDate.now().minusYears(5), "Very Bad", BigDecimal.valueOf(300), "I'm eat red hat"),
                        new AbstractAnimal("WOLF", "HomeWolf", "Balloon", LocalDate.now().minusYears(3), "Pretty", BigDecimal.valueOf(450), "he is small"),
                        new AbstractAnimal("WOLF", "Dingo", "Red", LocalDate.now().minusYears(8), "Evil", BigDecimal.valueOf(200), "secretInfo"),
                        new AbstractAnimal("WOLF", "Dingo", "Red", LocalDate.now().minusYears(8), "Evil", BigDecimal.valueOf(200), "secretInfo")
                );
            }
        };
    }

}