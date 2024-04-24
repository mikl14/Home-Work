package ru.mtsbank.fintech.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yaml")
public class DatabaseConfiguration {
    @Value("jdbc:postgresql://localhost:5432/" + "${Psql-connect.base-data.database}")
    private String dataBaseURL;
    @Value("${Psql-connect.base-data.password}")
    private String password;
    @Value("${Psql-connect.base-data.user}")
    private String user;

    public String getDataBaseURL() {
        return dataBaseURL;
    }

    public String getPassword() {
        return password;
    }

    public String getUser() {
        return user;
    }
}
