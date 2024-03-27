package ru.mtsbank.fintech.animal_repository;

import org.springframework.stereotype.Service;
import ru.mts.animals.AbstractAnimal;
import ru.mts.animals_creators.CreateAnimalServiceImpl;
import ru.mtsbank.fintech.exceptions.IllegalListSizeException;
import ru.mtsbank.fintech.exceptions.IllegalValueException;

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
     *
     * @return Map<String, LocalDate> ключ: тип + имя животного, значение: дата рождения
     */
    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        Map<String, LocalDate> leapYearBirthAnimal = new HashMap<>();

        for (Map.Entry<String, List<AbstractAnimal>> entry : animalMap.entrySet()) {
            leapYearBirthAnimal.putAll(entry.getValue().stream().distinct()
                    .filter(value -> value.getBirthDate().isLeapYear())
                    .collect(Collectors.toMap(AbstractAnimal::getName, AbstractAnimal::getBirthDate, (existingValue, newValue) -> newValue)));
        }
        return leapYearBirthAnimal;
    }

    /**
     * <b>findOlderAnimal</b>
     * возвращает Map животных, старше заданного возраста или самое взрослое животное
     *
     * @param age искомый возраст
     * @return Map<AbstractAnimal, Integer> - ключ: животное, значение: возраст
     */
    @Override
    public Map<AbstractAnimal, Integer> findOlderAnimal(int age) {
        if (age < 0) throw new IllegalValueException("Incorrect Age!");
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
            AbstractAnimal olderAnimal = optionalOlderAnimal.orElseThrow(() -> new IllegalValueException("Can't find the oldest animal!"));
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
        if (animalList.isEmpty()) throw new IllegalValueException("animalList is empty!");
        return animalList.stream().mapToLong(AbstractAnimal::getAge).average().orElse(0);
    }

    /**
     * <b>findOldAndExpensive</b>
     *
     * @return List<AbstractAnimal> старше olds и с ценой выше средней
     */
    public List<AbstractAnimal> findOldAndExpensive(int olds, List<AbstractAnimal> animalList) throws IllegalListSizeException {
        if (olds < 0) throw new IllegalValueException("Incorrect olds!");
        if (animalList.isEmpty()) throw new IllegalListSizeException("animalList is empty!");

        double averagePrice = animalList.stream()
                .mapToDouble(buf -> buf.getCost().doubleValue())
                .average()
                .orElse(0.0);

        List<AbstractAnimal> result = animalList.stream()
                .filter(animal -> animal.getAge() > olds && animal.getCost().doubleValue() > averagePrice)
                .sorted(Comparator.comparing(AbstractAnimal::getAge).reversed())
                .collect(Collectors.toList());

        return result;
    }

    /**
     * <b>findMinConstAnimals</b>
     *
     * @return List<AbstractAnimal> с limit самыми дешевыми животными отсортированный в обратном алфавитном порядке по именам
     */
    public List<String> findMinConstAnimals(List<AbstractAnimal> animalList, int limit) throws IllegalListSizeException {
        if (animalList.isEmpty() || animalList.size() < limit) throw new IllegalListSizeException("Incorrect list size!");
        return animalList.stream()
                .sorted(Comparator.comparing(AbstractAnimal::getCost))
                .limit(limit)
                .sorted(Comparator.comparing(AbstractAnimal::getName).reversed())
                .map(AbstractAnimal::getName)
                .collect(Collectors.toList());
    }
}
