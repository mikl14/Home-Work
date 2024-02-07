package ru.mts.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.mts.animalsCreators.CreateAnimalServiceImpl;

@Configuration
@Component
public class ConfigurationApp {
    @Scope("prototype")
    public CreateAnimalServiceImpl createAnimalServiceImpl() {
        return new CreateAnimalServiceImpl();
    }
}
