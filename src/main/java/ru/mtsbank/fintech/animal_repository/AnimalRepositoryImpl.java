package ru.mtsbank.fintech.animal_repository;

import org.springframework.stereotype.Service;
import ru.mts.animals.AbstractAnimal;
import ru.mts.animals_creators.CreateAnimalServiceImpl;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
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
     * @return Map<String, LocalDate> ключ: тип + имя животного, значение: дата рождения
     */
    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        Map<String, LocalDate> leapYearBirthAnimal = new HashMap<>();

        for (Map.Entry<String, List<AbstractAnimal>> entry : animalMap.entrySet()) {
            leapYearBirthAnimal.putAll(entry.getValue().stream().distinct()
                    .filter(value -> value.getBirthDate().isLeapYear())
                    .collect(Collectors.toMap(AbstractAnimal::getName, AbstractAnimal::getBirthDate,(existingValue, newValue) -> newValue)));
        }
        return leapYearBirthAnimal;
    }

    /**
     * <b>findOlderAnimal</b>
     * возвращает Map животных, старше заданного возраста или самое взрослое животное
     * @param age искомый возраст
     * @return Map<AbstractAnimal, Integer> - ключ: животное, значение: возраст
     */
    @Override
    public Map<AbstractAnimal, Integer> findOlderAnimal(int age) {
        if (age < 0) throw new IllegalArgumentException();
        Map<AbstractAnimal, Integer> olderAnimals = new HashMap<>();

        List<AbstractAnimal> animalList = new ArrayList<>();
        for (Map.Entry<String, List<AbstractAnimal>> entry : animalMap.entrySet()) {
            animalList.addAll(entry.getValue());
        }

        olderAnimals.putAll(animalList.stream().distinct()
                .filter(value -> value.getAge() > age)
                .collect(Collectors.toMap(value -> value, AbstractAnimal::getAge)));

        if (olderAnimals.isEmpty()) {

            Optional<AbstractAnimal> optionalOlderAnimal = animalList.stream().max(Comparator.comparing(AbstractAnimal::getAge));
            AbstractAnimal olderAnimal = optionalOlderAnimal.orElseThrow(() -> new IllegalArgumentException("ss"));
            olderAnimals.put(olderAnimal, olderAnimal.getAge());
        }
        return olderAnimals;
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

    /**
     * <b>findAverageAge</b>
     *
     * @return double средний возраст животных в переданном списке
     */
    public double findAverageAge(List<AbstractAnimal> animalList) {
        LocalDate currentDate = LocalDate.now();
        return animalList.stream().mapToLong(animal -> currentDate.getYear() - animal.getBirthDate().getYear()).average().orElse(0);
    }

    /**
     * <b>findOldAndExpensive</b>
     *
     * @return List<AbstractAnimal> старше olds и с ценой выше средней
     */
    public List<AbstractAnimal> findOldAndExpensive(int olds, List<AbstractAnimal> animalList) {
        if (olds < 0) throw new IllegalArgumentException();
        LocalDate currentDate = LocalDate.now();

        return animalList.stream().filter(animal -> (currentDate.getYear() - animal.getBirthDate().getYear()) > olds)
                .filter(animal -> animal.getCost().longValue() > animalList.stream()
                        .mapToLong(buf -> buf.getCost().longValue())
                        .average().orElse(0))
                .sorted(Comparator.comparing(AbstractAnimal::getBirthDate).reversed())
                .collect(Collectors.toList());
    }

    /**
     * <b>findMinConstAnimals</b>
     *
     * @return List<AbstractAnimal> с limit самыми дешевыми животными отсортированный в обратном алфавитном порядке по именам
     */
    public List<String> findMinConstAnimals(List<AbstractAnimal> animalList,int limit) {
        return animalList.stream()
                .sorted(Comparator.comparing(AbstractAnimal::getCost).reversed())
                .limit(limit)
                .map(AbstractAnimal::getName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
}
