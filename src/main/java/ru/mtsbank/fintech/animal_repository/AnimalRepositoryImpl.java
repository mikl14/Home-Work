package ru.mtsbank.fintech.animal_repository;

import org.springframework.stereotype.Service;
import ru.mts.animals.AbstractAnimal;
import ru.mts.animals_creators.AnimalFactory;
import ru.mts.animals_creators.CreateAnimalServiceImpl;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnimalRepositoryImpl implements AnimalRepository {

    private Map<String, List<AbstractAnimal>> animalMap;

    private CreateAnimalServiceImpl createAnimalService;

    /**
     * <b>AnimalRepositoryImpl</b>
     * Передается бин CreateAnimalServiceLmpl и заполняется animalArray
     *
     * @param createAnimalServiceImpl
     */

    public AnimalRepositoryImpl(CreateAnimalServiceImpl createAnimalServiceImpl) {
        createAnimalService = createAnimalServiceImpl;
    }


    /**
     * <b>init</b> запускается после конструктора
     * Заполняет массив animalArray 10 случайными животными
     */
    @PostConstruct
    public void init() {
        animalMap = createAnimalService.getAnimals();
    }

    public Map<String, List<AbstractAnimal>> getAnimalArray() {
        return animalMap;
    }

    /**
     * <b>findLeapYearNames</b> выполняет поиск животных рожденных в високосный год, по массиву животных
     *
     * @return Map<String, LocalDate> ключ: тип + имя животного, значение: дата рождения
     */
    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        Map<String, LocalDate> leapYearBirthAnimal = new HashMap<>();

        for (Map.Entry<String, List<AbstractAnimal>> entry : animalMap.entrySet()) {
            List<AbstractAnimal> animalList = entry.getValue().stream()
                    .filter(value -> value.getBirthDate().isLeapYear())
                    .collect(Collectors.toList());

            for (AbstractAnimal animal : animalList) {
                leapYearBirthAnimal.put(animal.getName(), animal.getBirthDate());
            }

        }
        return leapYearBirthAnimal;
    }

    /**
     * <b>findOlderAnimal</b>
     *
     * @param age искомый возраст
     * @return Map<AbstractAnimal, Integer> - ключ: животное, значение: возраст
     */
    @Override
    public Map<AbstractAnimal, Integer> findOlderAnimal(int age) {
        if (age < 0) throw new IllegalArgumentException();
        Map<AbstractAnimal, Integer> olderAnimal = new HashMap<>();
        LocalDate currentDate = LocalDate.now();

        for (Map.Entry<String, List<AbstractAnimal>> entry : animalMap.entrySet()) {
            List<AbstractAnimal> animalList = entry.getValue().stream()
                    .filter(value -> Period.between(value.getBirthDate(), currentDate).getYears() > age)
                    .collect(Collectors.toList());

            for (AbstractAnimal animal : animalList) {
                olderAnimal.put(animal, Period.between(animal.getBirthDate(), currentDate).getYears());
            }

        }
        return olderAnimal;
    }

    /**
     * <b>findDuplicate</b>
     *
     * @return Map<String, Integer> ключ: тип животного, значение: количество дубликатов
     */
    @Override
    public Map<String, List<AbstractAnimal>> findDuplicate() {
        return animalMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().stream()
                        .filter(animal -> entry.getValue().indexOf(animal) != entry.getValue().lastIndexOf(animal))
                        .collect(Collectors.toList())));
    }

    public double findAverageAge(List<AbstractAnimal> animalList)
    {
        LocalDate currentDate = LocalDate.now();

        return animalList.stream().mapToLong(animal -> currentDate.getYear() - animal.getBirthDate().getYear()).average().orElse(0);
    }
}
