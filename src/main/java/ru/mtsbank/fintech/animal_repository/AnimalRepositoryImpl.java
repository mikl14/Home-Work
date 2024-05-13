package ru.mtsbank.fintech.animal_repository;

import org.springframework.stereotype.Service;
import ru.mtsbank.fintech.entity.Animal;
import ru.mtsbank.fintech.exceptions.IllegalListSizeException;
import ru.mtsbank.fintech.exceptions.IllegalValueException;
import ru.mtsbank.fintech.service.AnimalService;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
public class AnimalRepositoryImpl {
    private AnimalService animalService;

    /**
     * <b>AnimalRepositoryImpl</b>
     * Передается бин AnimalService
     */

    public AnimalRepositoryImpl(AnimalService animalService) {
        this.animalService = animalService;
    }

    /**
     * <b>findLeapYearNames</b> выполняет поиск животных рожденных в високосный год, взятых из базы
     *
     * @return Map<String, LocalDate> ключ: тип + имя животного, значение: дата рождения
     */

    public Map<String, LocalDate> findLeapYearNames() {
        Map<String, List<Animal>> animalMap = getAnimals();
        Map<String, LocalDate> leapYearBirthAnimal = new ConcurrentHashMap<>();

        for (Map.Entry<String, List<Animal>> entry : animalMap.entrySet()) {
            leapYearBirthAnimal.putAll(entry.getValue().stream().distinct()
                    .filter(value -> value.getBirthDate().isLeapYear())
                    .collect(Collectors.toConcurrentMap(Animal::getName, Animal::getBirthDate, (existingValue, newValue) -> newValue)));
        }
        return leapYearBirthAnimal;
    }

    /**
     * <b>findOlderAnimal</b>
     * возвращает Map животных, старше заданного возраста или самое взрослое животное
     *
     * @param age искомый возраст
     * @return Map<Animal, Integer> - ключ: животное, значение: возраст
     */

    public Map<Animal, Integer> findOlderAnimal(int age) {
        if (age < 0) throw new IllegalValueException("Incorrect Age!");
        Map<Animal, Integer> olderAnimals = new ConcurrentHashMap<>();
        Map<String, List<Animal>> animalMap = getAnimals();

        List<Animal> animalList = new ArrayList<>();
        for (Map.Entry<String, List<Animal>> entry : animalMap.entrySet()) {
            animalList.addAll(entry.getValue());
        }

        olderAnimals.putAll(animalList.stream().distinct()
                .filter(value -> value.getAge() > age)
                .collect(Collectors.toConcurrentMap(value -> value, Animal::getAge)));

        if (olderAnimals.isEmpty()) {

            Optional<Animal> optionalOlderAnimal = animalList.stream().max(Comparator.comparing(Animal::getAge));
            Animal olderAnimal = optionalOlderAnimal.orElseThrow(() -> new IllegalValueException("Can't find the oldest animal!"));
            olderAnimals.put(olderAnimal, olderAnimal.getAge());
        }
        return olderAnimals;
    }

    /**
     * <b>findDuplicate</b>
     *
     * @return Map<String, Integer> ключ: тип животного, значение: количество дубликатов
     */

    public Map<String, List<Animal>> findDuplicate() {
        Map<String, List<Animal>> animalMap = getAnimals();
        Map<String, List<Animal>> result = animalMap.entrySet().stream()
                .collect(Collectors.toConcurrentMap(Map.Entry::getKey, entry -> entry.getValue().stream()
                        .filter(animal -> entry.getValue().indexOf(animal) != entry.getValue().lastIndexOf(animal))
                        .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::synchronizedList))));

        return result;
    }

    /**
     * <b>findAverageAge</b>
     *
     * @return double средний возраст животных в переданном списке
     */
    public double findAverageAge(List<Animal> animalList) {
        if (animalList.isEmpty()) throw new IllegalValueException("animalList is empty!");

        double result = animalList.stream().mapToLong(Animal::getAge).average().orElse(0);
        return result;
    }

    /**
     * <b>findOldAndExpensive</b>
     *
     * @return List<AbstractAnimal> старше olds и с ценой выше средней
     */
    public List<Animal> findOldAndExpensive(int olds, List<Animal> animalList) throws IllegalListSizeException {
        if (olds < 0) throw new IllegalValueException("Incorrect olds!");
        if (animalList.isEmpty()) throw new IllegalListSizeException("animalList is empty!");

        double averagePrice = animalList.stream()
                .mapToDouble(buf -> buf.getCost().doubleValue())
                .average()
                .orElse(0.0);

        List<Animal> result = animalList.stream()
                .filter(animal -> animal.getAge() > olds && animal.getCost().doubleValue() > averagePrice)
                .sorted(Comparator.comparing(Animal::getAge).reversed())
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::synchronizedList));

        return result;
    }

    /**
     * <b>findMinConstAnimals</b>
     *
     * @return List<AbstractAnimal> с limit самыми дешевыми животными отсортированный в обратном алфавитном порядке по именам
     */
    public List<String> findMinConstAnimals(List<Animal> animalList, int limit) throws IllegalListSizeException {
        if (animalList.isEmpty() || animalList.size() < limit)
            throw new IllegalListSizeException("Incorrect list size!");

        List<String> result = animalList.stream()
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(limit)
                .sorted(Comparator.comparing(Animal::getName).reversed())
                .map(Animal::getName)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::synchronizedList));

        return result;
    }

    /**
     * <b>getAnimals</b>
     *
     * @return Map<String, List < Animal>> со всеми животными находящимися в базе
     */

    public Map<String, List<Animal>> getAnimals() {
        Map<String, List<Animal>> animalMap = new ConcurrentHashMap<>();

        List<Animal> animals = animalService.getAllAnimals();
        try {
            for (Animal animal : animals) {
                if (!animalMap.containsKey(animal.getAnimalType().toString())) {
                    animalMap.put(animal.getAnimalType().toString(), new CopyOnWriteArrayList<>());
                }
                animalMap.get(animal.getAnimalType().toString()).add(animal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return animalMap;
    }

}


