package ru.mtsbank.fintech;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mtsbank.fintech.database.DatabaseConnection;

import java.text.SimpleDateFormat;

@Component
public class ScheduledTasks {
    private DatabaseConnection databaseConnection;
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public ScheduledTasks(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    /**
     * <b>databaseScheduledLog</b>
     * Выводит все записи из таблиц в базе раз в 20 секунд
     */
    @Scheduled(fixedRate = 1000 * 20) // 20 секунд
    public void databaseScheduledLog() {
        try {
            log.info("Base Record of Creatures: " + databaseConnection.getCreatures().toString());
            log.info("Base Record of Provides: " + databaseConnection.getProviders().toString());
        } catch (Exception e) {
            log.error("Exception! : " + e.getMessage(), e);
        }
    }
}