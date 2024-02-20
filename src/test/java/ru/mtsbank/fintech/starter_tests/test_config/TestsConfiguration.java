package ru.mtsbank.fintech.starter_tests.test_config;

import org.springframework.context.annotation.Bean;
import ru.mts.animals_creators.CreateAnimalServiceImpl;
import ru.mtsbank.fintech.animal_repository.AnimalRepositoryImpl;

@org.springframework.boot.test.context.TestConfiguration

public class TestsConfiguration {

    @Bean
    AnimalRepositoryImpl animalRepository(CreateAnimalServiceImpl createAnimalService) {
        return new AnimalRepositoryImpl(createAnimalService);
    }

}
