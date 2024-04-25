package ru.mtsbank.fintech;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mtsbank.fintech.animal_repository.AnimalRepositoryImpl;
import ru.mtsbank.fintech.animal_repository.FileConstants;
import ru.mtsbank.fintech.database.DatabaseConnection;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class ScheduledTasks {
    private AnimalRepositoryImpl animalRepository;

    private DatabaseConnection databaseConnection;
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public ScheduledTasks(AnimalRepositoryImpl animalRepository,DatabaseConnection databaseConnection) {
        this.animalRepository = animalRepository;
        this.databaseConnection = databaseConnection;
    }

    @PostConstruct
    public void init() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        Runnable findDuplicateTask = () -> {
            animalRepository.findDuplicate(); //вызываем метод
            log.info("FindDuplicate animal {}", animalRepository.readFromFile(FileConstants.findDuplicateFileName, Map.class)); //выводим данные
        };

        Runnable findLeapYearNamesTask = () -> {
            animalRepository.findLeapYearNames();//вызываем метод
            log.info("FindLeapYearNames animal {}", animalRepository.readFromFile(FileConstants.findLeapYearNamesFileName, Map.class));//выводим данные
        };

        executor.scheduleAtFixedRate(findDuplicateTask, 0, 1, TimeUnit.MINUTES);
        executor.scheduleAtFixedRate(findLeapYearNamesTask, 0, 2, TimeUnit.MINUTES);
    }

    /**
     * <b>databaseScheduledLog</b>
     * Выводит все записи из таблиц в базе раз в 20 секунд
     */
    @Scheduled(fixedRate = 1000 * 20) // 20 секунд
    public void databaseScheduledLog() {
        try {
            databaseConnection.DatabaseConnection();
            log.info("Base Record of Creatures: " + DatabaseConnection.getCreatures().toString());
            log.info("Base Record of Provides: " + DatabaseConnection.getProviders().toString());
        } catch (Exception e) {
            log.error("Exception! : " + e.getMessage(), e);
        }
    }
}