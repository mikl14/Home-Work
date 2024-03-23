package ru.mtsbank.fintech;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.animals.AbstractAnimal;
import ru.mtsbank.fintech.animal_repository.AnimalRepositoryImpl;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        try {

            List<AbstractAnimal> animalList = animalRepository.getAnimalArray().values().stream().max(Comparator.comparingInt(List::size)).orElse(List.of());
            //т.к. нельзя предугадать сколько будет сгенерировано животных, для передачи в методы взят самый длинный список из map животных.
            log.info("FindAverageAge {}", animalRepository.findAverageAge(animalList));
            log.info("FindOlder animal {}", animalRepository.findOlderAnimal(5));
            log.info("FindMinConstAnimals {}", animalRepository.findMinConstAnimals(animalList,animalList.size()));
            log.info("FindOldAndExpensive {}", animalRepository.findOldAndExpensive(5, animalList));

        } catch (Exception e) {
            log.error("Exception! : " + e.getMessage(),e);
        }

    }
}