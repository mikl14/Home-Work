package ru.mtsbank.fintech.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.mtsbank.fintech.database_objects.Creature;
import ru.mtsbank.fintech.database_objects.Provider;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseConnection {

    private String DB_URL;
    private String DB_USER;
    private String DB_PASSWORD;
    private static Connection connection;
    private static final Logger log = LoggerFactory.getLogger(DatabaseConnection.class);

    public DatabaseConnection(DatabaseProperties properties) {
        DB_URL = properties.getDataBaseURL();
        DB_USER = properties.getUser();
        DB_PASSWORD = properties.getPassword();
    }

    @PostConstruct
    public void init() {
        DatabaseConnection();
    }

    /**
     * <b>DatabaseConnection</b>
     * устанавливает соединение с базой
     *
     * @throws SQLException
     */

    private void DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            log.error("Ошибка на этапе подключения к базе! " + e);
        }
    }

    /**
     * <b>getTableRecord</b>
     * возвращает результаты запроса query к базе
     *
     * @param query
     * @return ResultSet
     * @throws SQLException
     */
    private ResultSet getTableRecord(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    /**
     * <b>getCreatures</b>
     *
     * @return список всех объектов полученных из таблицы animals.creature
     * @throws SQLException
     */
    public List<Creature> getCreatures() throws SQLException {
        ResultSet resultSet = getTableRecord(
                "SELECT id_creature,name,type,age,area FROM animals.creature " +
                        "JOIN animals.animal_type ON type_id = id_type " +
                        "JOIN animals.animals_habitats ON type_id = animals.animals_habitats.id_animal_type " +
                        "JOIN animals.habitats ON animals.animals_habitats.id_area = animals.habitats.id_area");
        List<Creature> records = new ArrayList<>();
        while (resultSet.next()) {
            Creature creature = new Creature();
            creature.setId(BigDecimal.valueOf(resultSet.getInt("id_creature")));
            creature.setName(resultSet.getString("name"));
            creature.setType(resultSet.getString("type"));
            creature.setAge(resultSet.getInt("age"));
            creature.setArea(resultSet.getString("area"));
            records.add(creature);
        }
        resultSet.close();
        return records;
    }

    /**
     * <b>getProviders</b>
     *
     * @return список всех объектов полученных из таблицы animals.provider
     * @throws SQLException
     */
    public List<Provider> getProviders() throws SQLException {
        ResultSet resultSet = getTableRecord(
                "SELECT animals.provider.id_provider,name,phone,type as animal_type " +
                        "FROM animals.provider " +
                        "JOIN animals.animals_provider ON provider.id_provider = animals_provider.id_provider " +
                        "JOIN animals.animal_type ON id_animal_type = animals.animal_type.id_type");

        List<Provider> records = new ArrayList<>();
        while (resultSet.next()) {
            Provider provider = new Provider();
            provider.setIdProvider(resultSet.getInt("id_provider"));
            provider.setName(resultSet.getString("name"));
            provider.setPhone(resultSet.getString("phone"));
            provider.setAnimalType(resultSet.getString("animal_type"));
            records.add(provider);
        }
        resultSet.close();
        return records;
    }
}
