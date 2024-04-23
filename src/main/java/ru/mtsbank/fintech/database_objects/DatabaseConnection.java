package ru.mtsbank.fintech.database_objects;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseConnection {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/animalbase";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "schef2002";

    /**
     * <b>getTableRecord</b>
     * возвращает результаты запроса query к базе
     *
     * @param query
     * @return ResultSet
     * @throws SQLException
     */
    private static ResultSet getTableRecord(String query) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    /**
     * <b>getCreatures</b>
     *
     * @return список всех объектов полученных из таблицы animals.creature
     * @throws SQLException
     */
    public static List<TableRecord> getCreatures() throws SQLException {
        ResultSet resultSet = getTableRecord("SELECT id_creature, name,type_id,age FROM animals.creature");
        List<TableRecord> records = new ArrayList<>();
        while (resultSet.next()) {
            Creature creature = new Creature();
            creature.setId(BigDecimal.valueOf(resultSet.getInt("id_creature")));
            creature.setName(resultSet.getString("name"));
            creature.setTypeId(resultSet.getInt("type_id"));
            creature.setAge(resultSet.getInt("age"));
            records.add(creature);
        }
        resultSet.close();
        return records;
    }

    /**
     * <b>getHabitats</b>
     *
     * @return список всех объектов полученных из таблицы animals.habitats
     * @throws SQLException
     */
    public static List<TableRecord> getHabitats() throws SQLException {
        ResultSet resultSet = getTableRecord("SELECT id_area,area FROM animals.habitats");
        List<TableRecord> records = new ArrayList<>();
        while (resultSet.next()) {
            Habitats habitat = new Habitats();
            habitat.setIdArea(resultSet.getInt("id_area"));
            habitat.setArea(resultSet.getString("area"));
            records.add(habitat);
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
    public static List<TableRecord> getProviders() throws SQLException {
        ResultSet resultSet = getTableRecord("SELECT id_provider,name,phone FROM animals.provider");
        List<TableRecord> records = new ArrayList<>();
        while (resultSet.next()) {
            Provider provider = new Provider();
            provider.setIdProvider(resultSet.getInt("id_provider"));
            provider.setName(resultSet.getString("name"));
            provider.setPhone(resultSet.getString("phone"));
            records.add(provider);
        }
        resultSet.close();
        return records;
    }

    /**
     * <b>getAnimalTypes</b>
     *
     * @return список всех объектов полученных из таблицы animals.animal_type
     * @throws SQLException
     */
    public static List<TableRecord> getAnimalTypes() throws SQLException {
        ResultSet resultSet = getTableRecord("SELECT id_type,type,is_wild FROM animals.animal_type");
        List<TableRecord> records = new ArrayList<>();
        while (resultSet.next()) {
            AnimalType animalType = new AnimalType();
            animalType.setIdType(resultSet.getInt("id_type"));
            animalType.setType(resultSet.getString("type"));
            animalType.setWild(resultSet.getBoolean("is_wild"));
            records.add(animalType);
        }
        resultSet.close();
        return records;
    }
}
