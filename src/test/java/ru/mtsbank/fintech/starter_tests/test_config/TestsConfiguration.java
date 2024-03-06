package ru.mtsbank.fintech.starter_tests.test_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.boot.test.context.TestConfiguration;
import ru.mts.animals.AbstractAnimal;
import ru.mts.animals.Bear;
import ru.mts.animals.Cat;
import ru.mts.animals.Fish;
import ru.mts.animals_creators.AnimalFactory;
import ru.mts.animals_creators.CreateAnimalServiceImpl;
import ru.mtsbank.fintech.animal_repository.AnimalRepositoryImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@TestConfiguration

public class TestsConfiguration {

    @Scope("singleton")
    @Bean
    CreateAnimalServiceImpl createAnimalServiceImpl(AnimalFactory animalFactory) {
        return new CreateAnimalServiceImpl(animalFactory) {
            @Override
            public Map<String, List<AbstractAnimal>> getAnimals() {
                return new HashMap<String, List<AbstractAnimal>>() {{
                    put("FISH", List.of(
                            new Fish("Abis", "Boris", "Evil", LocalDate.now().minusYears(5), BigDecimal.valueOf(123), "meat", 12),
                            new Fish("Abis", "Blade", "Evil", LocalDate.now().minusYears(7), BigDecimal.valueOf(123), "meat", 12),
                            new Fish("Not abris", "Not Pan", "Evil", LocalDate.now().minusYears(9), BigDecimal.valueOf(123), "meat", 12),
                            new Fish("Great", "Piter Pan", "Evil", LocalDate.now().minusYears(11), BigDecimal.valueOf(123), "meat", 12)));
                    put("CAT", List.of(
                            new Cat("Great", "Piter", "Evil", LocalDate.now().minusYears(10), BigDecimal.valueOf(123), "meat", 12),
                            new Cat("Abis", "Pan", "Evil", LocalDate.now().minusYears(10), BigDecimal.valueOf(123), "meat", 12),
                            new Cat("Abis", "Pan", "Evil", LocalDate.now().minusYears(10), BigDecimal.valueOf(123), "meat", 12),
                            new Cat("Abis", "Pan", "Good", LocalDate.now().minusYears(10), BigDecimal.valueOf(123), "meat", 12)));
                    put("BEAR", List.of(
                            new Bear("White", "Beluga", "Evil", LocalDate.now().minusYears(8), BigDecimal.valueOf(123), "forest", 120)));
                }};
            }
        };
    }

    @Scope("singleton")
    @Bean
    AnimalRepositoryImpl animalRepository(CreateAnimalServiceImpl createAnimalServiceImpl) {
        return new AnimalRepositoryImpl(createAnimalServiceImpl);
    }

}