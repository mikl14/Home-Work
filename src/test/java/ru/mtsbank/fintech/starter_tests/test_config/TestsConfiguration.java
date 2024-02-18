package ru.mtsbank.fintech.starter_tests.test_config;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mts.animals_creators.CreateAnimalServiceImpl;
import ru.mtsbank.fintech.animal_repository.AnimalRepositoryImpl;

@org.springframework.boot.test.context.TestConfiguration

public class TestsConfiguration {

    @Autowired
    AnimalRepositoryImpl animalRepository(CreateAnimalServiceImpl createAnimalService) {
        return new AnimalRepositoryImpl(createAnimalService);
    }

}
