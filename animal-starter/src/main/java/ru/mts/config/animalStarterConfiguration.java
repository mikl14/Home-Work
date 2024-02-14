package ru.mts.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mts.animals_creators.AnimalFactory;
import ru.mts.animals_creators.CreateAnimalServiceImpl;

@Configuration
@EnableConfigurationProperties(animalStarterProperties.class)
public class animalStarterConfiguration {

    @Bean
    public CreateAnimalServiceImpl createAnimalService(AnimalFactory animalFactory) {
        return new CreateAnimalServiceImpl(animalFactory);
    }

    @Bean
    public AnimalFactory animalFactory(animalStarterProperties properties) {
        return new AnimalFactory(properties);
    }
}
