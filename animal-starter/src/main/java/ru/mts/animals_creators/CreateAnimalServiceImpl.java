package ru.mts.animals_creators;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import ru.mts.animals.AbstractAnimal;
import ru.mts.exceptions.FileAccessException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class CreateAnimalServiceImpl implements CreateAnimalService {
    private AnimalFactory animalFactory;

    private AnimalFactory.AnimalType animalType; // хранит тип животного который вернет getAnimal()

    public void setAnimalType(AnimalFactory.AnimalType animalType) {
        this.animalType = animalType;
    }

    public AnimalFactory.AnimalType getAnimalType() {
        return animalType;
    }

    public CreateAnimalServiceImpl(AnimalFactory animalFactory) {
        this.animalFactory = animalFactory;
    }

    /**
     * <b>getRandomAnimal</b>
     * возвращает случайное животное
     *
     * @return AbstractAnimal
     */
    public AbstractAnimal getRandomAnimal() {

        AbstractAnimal animal = animalFactory.getAnimal();

        try {
            Resource resource = new ClassPathResource("results");       // получаем ресурс results
            Path resourceFolderPath;
            if (!resource.exists()) {
                Resource resourceInResFolder = new ClassPathResource("application.yaml"); // не нашел иного способа получить путь до папки resources
                resourceFolderPath = Path.of(Path.of(resourceInResFolder.getFile().getAbsolutePath()).getParent() + "/results");  // объявляем новый путь
                Files.createDirectory(resourceFolderPath.toAbsolutePath()); // создаем директорию
            }

            File fileToWrite = new File(resource.getFile().getAbsolutePath() + "/allAnimals.txt");

            Path path = fileToWrite.toPath();
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            int rowNumber = Files.readAllLines(path).size() + 1;
            Files.write(path, (rowNumber + " " + animal.getClass().getSimpleName() + " " + animal.getName() + " " + animal.getCost() + " " + animal.getBirthDate() + '\n').getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new FileAccessException("Ошибка доступа к файлу со всеми животными!" + e);
        }
        return animal;
    }

    /**
     * <b>getAnimal</b>
     * возвращает случайное животное заданного типа
     *
     * @return AbstractAnimal
     */
    public AbstractAnimal getAnimal() {
        AbstractAnimal animal = animalFactory.getAnimal(animalType);
        String type = animal.getClass().getSimpleName().toUpperCase(Locale.ROOT);
        if (animalType.toString().equals(type)) return animal;
        else throw new IllegalStateException();

    }

    /**
     * <b>getAnimals</b>
     *
     * @param numberAnimals количество уникальных животных которых необходимо создать
     * @return Массив животных длинной numberAnimals
     */
    public Map<String, List<AbstractAnimal>> getAnimals(int numberAnimals) {

        if (numberAnimals < 0) throw new IllegalArgumentException();
        ConcurrentHashMap<String, List<AbstractAnimal>> animalMap = new ConcurrentHashMap<>();
        for (int i = 0; i < numberAnimals; i++) {
            AbstractAnimal animal = getRandomAnimal();
            if (!animalMap.containsKey(animal.getAnimalType())) {
                animalMap.put(animal.getAnimalType(), new CopyOnWriteArrayList<>());
            }
            animalMap.get(animal.getAnimalType()).add(animal);
        }
        return animalMap;
    }

    /**
     * <b>getAnimals()</b>
     * Перегружен в соответствии с т.з
     *
     * @return Массив животных длинной 10
     */
    @Override
    public Map<String, List<AbstractAnimal>> getAnimals() {

        int i = 0;
        ConcurrentHashMap<String, List<AbstractAnimal>> animalMap = new ConcurrentHashMap<>();
        do {
            AbstractAnimal animal = getRandomAnimal();
            if (!animalMap.containsKey(animal.getAnimalType())) {
                animalMap.put(animal.getAnimalType(), new CopyOnWriteArrayList<>());
            }
            animalMap.get(animal.getAnimalType()).add(animal);
            i++;
        }
        while (i < 10);

        return animalMap;

    }

    public List<AbstractAnimal> getAnimalsList(int numberAnimals) {

        if (numberAnimals < 0) throw new IllegalArgumentException();
        List<AbstractAnimal> animalList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < numberAnimals; i++) {
            AbstractAnimal animal = getRandomAnimal();
            animalList.add(animal);
        }
        return animalList;
    }

    public List<AbstractAnimal> getAnimalsList() {
        List<AbstractAnimal> animalList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            AbstractAnimal animal = getRandomAnimal();
            animalList.add(animal);
        }
        return animalList;
    }
}
