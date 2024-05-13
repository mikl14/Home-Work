package ru.mtsbank.fintech;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mtsbank.fintech.animal_repository.AnimalRepositoryImpl;
import ru.mtsbank.fintech.entity.Animal;
import ru.mtsbank.fintech.repositories.AnimalTypeRepository;
import ru.mtsbank.fintech.service.AnimalService;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

@Component
public class ScheduledTasks {
    private final AnimalRepositoryImpl animalRepository;
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    public ScheduledTasks(AnimalRepositoryImpl animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Scheduled(fixedRate = 1000 * 20) // 20 секунд
    public void ScheduledLog() {
        try {
            List<Animal> animalList = animalRepository.getAnimals().values().stream().max(Comparator.comparingInt(List::size)).orElse(List.of());

            //т.к. нельзя предугадать сколько будет сгенерировано животных, для передачи в методы взят самый длинный список из map животных.
            log.info("FindDuplicate animal {}", animalRepository.findDuplicate());
            log.info("FindLeapYearNames animal {}", animalRepository.findLeapYearNames());
            log.info("FindAverageAge {}", animalRepository.findAverageAge(animalList));
            log.info("FindOlder animal {}", animalRepository.findOlderAnimal(5));
            log.info("FindMinConstAnimals {}", animalRepository.findMinConstAnimals(animalList, animalList.size()));
            log.info("FindOldAndExpensive {}", animalRepository.findOldAndExpensive(5, animalList));
        } catch (Exception e) {
            log.error("Exception! : " + e.getMessage(), e);
        }
    }


}