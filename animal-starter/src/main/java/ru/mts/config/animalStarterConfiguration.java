package ru.mts.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mts.animalsCreators.AnimalFactory;
import ru.mts.animalsCreators.CreateAnimalServiceImpl;

@Configuration
@EnableConfigurationProperties(animalStarterProperties.class)
public class animalStarterConfiguration {

    @Bean
    public CreateAnimalServiceImpl createAnimalService() {
        return new CreateAnimalServiceImpl();
    }

    @Bean
    public AnimalFactory animalFactory() {
        return new AnimalFactory();
    }
}
