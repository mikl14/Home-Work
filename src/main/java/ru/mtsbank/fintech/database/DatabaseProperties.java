package ru.mtsbank.fintech.database;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "psql-connect.base-data")
public class DatabaseProperties {

    private String dataBaseURL;

    private String password;

    private String user;

    public String getDataBaseURL() {
        return dataBaseURL;
    }

    public void setDataBaseURL(String dataBaseURL) {
        this.dataBaseURL = dataBaseURL;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
