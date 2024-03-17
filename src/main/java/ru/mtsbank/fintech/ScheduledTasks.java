package ru.mtsbank.fintech;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mtsbank.fintech.animal_repository.AnimalRepositoryImpl;

import java.text.SimpleDateFormat;

@Component
public class ScheduledTasks {
    private AnimalRepositoryImpl animalRepository;
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public ScheduledTasks(AnimalRepositoryImpl animalRepository) {
        this.animalRepository = animalRepository;
    }

    /**
     * <b>animalScheduledLog</b>
     * Выводит все методы animalRepository 1 раз в минуту
     */
    @Scheduled(fixedRate = 1000 * 60) // 1 минута
    public void animalScheduledLog() {
        log.info("FindLeapYearNames animal {}", animalRepository.findLeapYearNames());

        log.info("FindDuplicate animal {}", animalRepository.findDuplicate());

        log.info("FindAverageAge {}", animalRepository.findAverageAge(animalRepository.getAnimalArray().get("CAT")));

        log.info("FindOldAndExpensive {}", animalRepository.findOldAndExpensive(5, animalRepository.getAnimalArray().get("CAT")));
        try {
            log.info("FindOlder animal {}", animalRepository.findOlderAnimal(10).toString());
            log.info("FindMinConstAnimals {}", animalRepository.findMinConstAnimals(animalRepository.getAnimalArray().get("CAT"), 3));
        } catch (Exception e) {
            System.out.println("Exception! : " + e);
        }

    }
}