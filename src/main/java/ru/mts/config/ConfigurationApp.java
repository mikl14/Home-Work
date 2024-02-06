package ru.mts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.animalsCreators.CreateAnimalServiceLmpl;

@Configuration
public class ConfigurationApp {
    @Bean
    @Scope("prototype")
    public CreateAnimalServiceLmpl createAnimalServiceLmpl() {
        return new CreateAnimalServiceLmpl();
    }
}
