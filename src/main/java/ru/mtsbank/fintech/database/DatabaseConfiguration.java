package ru.mtsbank.fintech.database;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@EnableConfigurationProperties(DatabaseProperties.class)
public class DatabaseConfiguration {

    @Scope("singleton")
    @Bean
    public DatabaseConnection databaseConnect(DatabaseProperties properties) {
        return new DatabaseConnection(properties);
    }

}
