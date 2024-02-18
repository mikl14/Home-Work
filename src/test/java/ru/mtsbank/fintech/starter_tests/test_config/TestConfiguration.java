package ru.mtsbank.fintech.starter_tests.test_config;

import org.springframework.context.annotation.Bean;
import ru.mts.animals_creators.AnimalFactory;
import ru.mts.animals_creators.CreateAnimalServiceImpl;
import ru.mts.config.AnimalStarterProperties;


@org.springframework.boot.test.context.TestConfiguration
public class TestConfiguration {
    @Bean
    public CreateAnimalServiceImpl createAnimalService(AnimalFactory animalFactory) {
        return new CreateAnimalServiceImpl(animalFactory);
    }

    @Bean
    public AnimalFactory animalFactory(AnimalStarterProperties properties) {
        return new AnimalFactory(properties);
    }

}
