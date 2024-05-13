package ru.mtsbank.fintech;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import ru.mtsbank.fintech.animal_repository.AnimalRepositoryImpl;
import ru.mtsbank.fintech.entity.Animal;
import ru.mtsbank.fintech.service.AnimalService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@TestConfiguration

public class TestsConfiguration {

    @Primary
    @Bean
    AnimalRepositoryImpl animalRepositoryImpli(AnimalService animalService) {
        return new AnimalRepositoryImpl(animalService) {
            @Override

            public Map<String, List<Animal>> getAnimals() {

                Map<String, List<Animal>> animalMap = new HashMap<>();

                animalMap.put("FISH",
                        List.of(new Animal("Gold Fish", "Goldie", LocalDate.now().minusYears(5), BigDecimal.valueOf(100)),
                                new Animal("Guppy", "Tiny", LocalDate.now().minusYears(7), BigDecimal.valueOf(200)),
                                new Animal("Magic Karp", "Slark", LocalDate.now().minusYears(9), BigDecimal.valueOf(250)),
                                new Animal("Shark", "Blue Dragon", LocalDate.now().minusYears(11), BigDecimal.valueOf(550))));

                animalMap.put("CAT",
                        List.of(new Animal("Persian", "Kitty", LocalDate.now().minusYears(10), BigDecimal.valueOf(330)),
                                new Animal("CyberCat", "V", LocalDate.now().minusYears(2), BigDecimal.valueOf(256)),
                                new Animal("Tibet", "Cloud", LocalDate.now().minusYears(4), BigDecimal.valueOf(300)),
                                new Animal("Stray", "Akira", LocalDate.now().minusYears(6), BigDecimal.valueOf(125))
                        ));

                animalMap.put("BEAR",
                        List.of(new Animal("Animatronic", "GoldenFreddy", LocalDate.now().minusYears(6), BigDecimal.valueOf(600)),
                                new Animal("White", "Beluga", LocalDate.now().minusYears(3), BigDecimal.valueOf(500)),
                                new Animal("Brown", "Ivan", LocalDate.now().minusYears(5), BigDecimal.valueOf(500)),
                                new Animal("White", "Beluga", LocalDate.now().minusYears(3), BigDecimal.valueOf(500))
                        ));

                animalMap.put("WOLF",
                        List.of(new Animal("Grey", "Ball", LocalDate.now().minusYears(5), BigDecimal.valueOf(300)),
                                new Animal("HomeWolf", "Balloon", LocalDate.now().minusYears(3), BigDecimal.valueOf(450)),
                                new Animal("Dingo", "Red", LocalDate.now().minusYears(8), BigDecimal.valueOf(200)),
                                new Animal("Dingo", "Red", LocalDate.now().minusYears(8), BigDecimal.valueOf(200))
                        ));

                return animalMap;
            }
        };
    }
}