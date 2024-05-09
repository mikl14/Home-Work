package ru.mtsbank.fintech;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mtsbank.fintech.entity.AnimalType;
import ru.mtsbank.fintech.repositories.AnimalTypeRepository;
import ru.mtsbank.fintech.service.AnimalService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Component
public class ScheduledTasks {
    private AnimalService animalService;
    private final AnimalTypeRepository animalTypeRepository;
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public ScheduledTasks(AnimalService animalService, AnimalTypeRepository animalTypeRepository) {
        this.animalService = animalService;
        this.animalTypeRepository = animalTypeRepository;
       // animalService.initAnimals(new AnimalType("CAT", false, new ArrayList<>()));
    }

    /**
     * <b>databaseScheduledLog</b>
     * Выводит все записи из таблиц в базе раз в 20 секунд
     */
    @Scheduled(fixedRate = 1000 * 20) // 20 секунд
    public void ScheduledLog() {
       // log.info(animalService.getAllAnimals().toString());
    }
}