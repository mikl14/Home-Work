package ru.mtsbank.fintech.animalRepository;

import org.springframework.stereotype.Service;
import ru.mts.Animals.AbstractAnimal;
import ru.mts.animalsCreators.CreateAnimalServiceImpl;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AnimalRepositoryImpl implements AnimalRepository {

    private AbstractAnimal[] animalArray;

    private CreateAnimalServiceImpl createAnimalService;
    /**
     * <b>AnimalRepositoryImpl</b>
     * Передается бин CreateAnimalServiceLmpl и заполняется animalArray
     * @param createAnimalServiceImpl
     */

    public AnimalRepositoryImpl(CreateAnimalServiceImpl createAnimalServiceImpl) {
        createAnimalService = createAnimalServiceImpl;
        System.out.println(createAnimalService);
    }
    /**
     * <b>init</b> запускается после конструктора
     * Заполняет массив animalArray
     */
    @PostConstruct
    public void init() {
        animalArray = createAnimalService.getAnimals();
    }

    public void setAnimalArray(AbstractAnimal[] animalArray) {
        this.animalArray = animalArray;
    }

    /**
     * <b>findLeapYearNames</b> выполняет поиск животных рожденных в високосный год, по массиву животных
     *
     * @return String[] имя животного + дата рождения в формате dd-MM-yyyy
     */
    @Override
    public String[] findLeapYearNames() {
        List<String> leapYearBirthAnimal = new ArrayList<>();
        for (AbstractAnimal animal : animalArray) {
            if (animal.getBirthDate().isLeapYear()) {
                leapYearBirthAnimal.add(animal.getName() + " " + animal.getFormatDate("dd-MM-yyyy"));
            }
        }
        return leapYearBirthAnimal.toArray(new String[0]);
    }

    /**
     * <b>findOlderAnimal</b>
     *
     * @param age искомый возраст
     * @return AbstractAnimal[] - массив зверей большего чем olderYears возраста
     */
    @Override
    public AbstractAnimal[] findOlderAnimal(int age) {
        if (age < 0) throw new IllegalArgumentException();
        List<AbstractAnimal> olderAnimal = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        for (AbstractAnimal animal : animalArray) {
            int olderYears = Period.between(animal.getBirthDate(), currentDate).getYears();
            if (olderYears > age) {
                olderAnimal.add(animal);
            }

        }
        return olderAnimal.toArray(new AbstractAnimal[0]);
    }

    /**
     * <b>findDuplicate</b>
     *
     * @return AbstractAnimal[] массив животных имеющих дубликаты в animalArray
     */
    @Override
    public AbstractAnimal[] findDuplicate() {
        List<AbstractAnimal> duplicates = new ArrayList<>();
        Set<AbstractAnimal> uniqueElements = new HashSet<>();

        for (AbstractAnimal animal : animalArray) {
            if (!uniqueElements.add(animal)) {
                duplicates.add(animal);
            }
        }
        return duplicates.toArray(new AbstractAnimal[0]);
    }


}
