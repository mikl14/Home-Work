package ru.mtsbank.fintech.animal_repository;

import org.springframework.stereotype.Service;
import ru.mts.animals.AbstractAnimal;
import ru.mts.animals_creators.CreateAnimalServiceImpl;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

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
    public Map<String,LocalDate> findLeapYearNames() {
        Map<String,LocalDate> leapYearBirthAnimal = new HashMap<>();
        for (Map.Entry<String, List<AbstractAnimal>> entry : animalMap.entrySet()) {
            for(AbstractAnimal animal : entry.getValue()) {
                if (animal.getBirthDate().isLeapYear()) {
                    leapYearBirthAnimal.put( animal.getAnimalType() + " "+ animal.getName(),animal.getBirthDate());
                }
            }
        }
        return leapYearBirthAnimal;
    }

    /**
     * <b>findOlderAnimal</b>
     *
     * @param age искомый возраст
     * @return Map<AbstractAnimal,Integer> - ключ: животное, значение: возраст
     */
    @Override
    public Map<AbstractAnimal,Integer> findOlderAnimal(int age) {
        if (age < 0) throw new IllegalArgumentException();
        Map<AbstractAnimal,Integer> olderAnimal = new HashMap<>();
        LocalDate currentDate = LocalDate.now();

        for (Map.Entry<String, List<AbstractAnimal>> entry : animalMap.entrySet()) {
            for(AbstractAnimal animal : entry.getValue())
            {
                int olderYears = Period.between(animal.getBirthDate(), currentDate).getYears();
                if (olderYears > age) {
                        olderAnimal.put(animal,olderYears);
                }
            }
        }
        return olderAnimal;
    }

    /**
     * <b>findDuplicate</b>
     *
     * @return  Map<String,Integer> ключ: тип животного, значение: количество дубликатов
     */
    @Override
    public Map<String,Integer> findDuplicate() {
        Map<String,Integer> duplicates = new HashMap<>();
        Set<AbstractAnimal> uniqueElements = new HashSet<>();

        for (Map.Entry<String, List<AbstractAnimal>> entry : animalMap.entrySet()) {
            for(AbstractAnimal animal : entry.getValue()) {
                if (!uniqueElements.add(animal)) {
                    int currentValue = duplicates.getOrDefault(animal.getAnimalType(),0);
                    duplicates.put(animal.getAnimalType(), currentValue + 1);
                }
            }
        }
        return duplicates;
    }


}
