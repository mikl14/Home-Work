package ru.mtsbank.fintech.animal_repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.mts.animals_creators.CreateAnimalServiceImpl;
import ru.mts.exceptions.FileAccessException;
import ru.mtsbank.fintech.entity.Animal;
import ru.mtsbank.fintech.entity.AnimalType;
import ru.mtsbank.fintech.exceptions.IllegalListSizeException;
import ru.mtsbank.fintech.exceptions.IllegalValueException;
import ru.mtsbank.fintech.util.HibernateUtil;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
public class AnimalRepositoryImpl implements AnimalRepository {


    private CreateAnimalServiceImpl createAnimalService;
    private ObjectMapper objectMapper;

    /**
     * <b>AnimalRepositoryImpl</b>
     * Передается бин CreateAnimalServiceLmpl и заполняется animalArray
     *
     * @param createAnimalServiceImpl
     */

    public AnimalRepositoryImpl(CreateAnimalServiceImpl createAnimalServiceImpl, ObjectMapper objectMapper) {
        createAnimalService = createAnimalServiceImpl;
        this.objectMapper = objectMapper;
    }


    /**
     * <b>init</b> запускается после конструктора
     * Заполняет массив animalArray 10 случайными животными
     */
    @PostConstruct
    public void init() {
        List<Animal> animalList = createAnimalService.getAnimalsList().stream().map(Animal::new).collect(Collectors.toList());
        saveAnimal(animalList);
    }

    /**
     * <b>findLeapYearNames</b> выполняет поиск животных рожденных в високосный год, по массиву животных
     *
     * @return Map<String, LocalDate> ключ: тип + имя животного, значение: дата рождения
     */
    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        Map<String, List<Animal>> animalMap = getAllAnimals();
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
    @Override
    public Map<Animal, Integer> findOlderAnimal(int age) {
        if (age < 0) throw new IllegalValueException("Incorrect Age!");
        Map<Animal, Integer> olderAnimals = new ConcurrentHashMap<>();
        Map<String, List<Animal>> animalMap = getAllAnimals();

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
    @Override
    public Map<String, List<Animal>> findDuplicate() {
        Map<String, List<Animal>> animalMap = getAllAnimals();
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
        writeToFile(result, FileConstants.findAverageAgeFileName);
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
     * <b>writeToFile</b>
     * принимает объект для записи в файл и имя файла
     * записывает объект в заданный файл
     */


    public void writeToFile(Object obj, String fileName) {
        try {
            Resource resource = new ClassPathResource("results");       // получаем ресурс results
            Path resourceFolderPath;
            if (!resource.exists()) {
                Resource resourceInResFolder = new ClassPathResource("application.yaml"); // не нашел иного способа получить путь до папки resources
                resourceFolderPath = Path.of(Path.of(resourceInResFolder.getFile().getAbsolutePath()).getParent() + "/results");  // объявляем новый путь
                Files.createDirectory(resourceFolderPath.toAbsolutePath()); // создаем директорию
            }
            resourceFolderPath = resource.getFile().toPath();
            File fileToWrite = new File(resourceFolderPath.toAbsolutePath() + "/" + fileName);
            objectMapper.writeValue(fileToWrite, obj);
        } catch (IOException e) {
            throw new FileAccessException("Ошибка создания или доступа к файлу для записи результата!" + e);
        }
    }

    /**
     * <b>readFromFile</b>
     * принимает имя файла для чтения и тип возвращаемого значения
     */
    public <T> T readFromFile(String fileName, Class<T> type) {
        try {
            Resource resource = new ClassPathResource("results/" + fileName);
            return objectMapper.readValue(resource.getFile(), type);
        } catch (IOException e) {
            throw new FileAccessException("Ошибка доступа к файлу для чтения результата!" + e);
        }
    }

    /**
     * <b>saveAnimal</b>
     * Записывает данные полученные из CreateAnimalService в базу
     */
    private void saveAnimal(List<Animal> animalList) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;
            transaction = session.beginTransaction();

            Set<AnimalType> animalTypes = animalList.stream().map(Animal::getAnimalType).collect(Collectors.toSet());


            for (AnimalType animalType : animalTypes) {
                for (Animal animal : animalList) {
                    if (animal.getAnimalType().equals(animalType)) {
                        animalType.addToAnimalList(animal);
                        animal.setAnimalType(animalType);
                    }
                }
            }

            for (AnimalType animalType : animalTypes) session.save(animalType);

            for (Animal animal : animalList) {
                session.save(animal.getBreed());
                session.save(animal);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <b>getAllAnimals</b>
     * Считывает все объекты Animal из базы данных
     */
    public Map<String, List<Animal>> getAllAnimals() {
        Map<String, List<Animal>> animalMap = new ConcurrentHashMap<>();

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Animal> animals = new ArrayList<>();
        try {
            animals = session.createQuery("FROM Animal", Animal.class).getResultList();
            for (Animal animal : animals) {
                if (!animalMap.containsKey(animal.getAnimalType().toString())) {
                    animalMap.put(animal.getAnimalType().toString(), new CopyOnWriteArrayList<>());
                }
                animalMap.get(animal.getAnimalType().toString()).add(animal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return animalMap;
    }
}


