package ru.mts.animalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mts.Animals.AbstractAnimal;
import ru.mts.animalSearch.SearchServiceLmpl;
import ru.mts.animalsCreators.CreateAnimalServiceLmpl;

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

    /**
     * <b>AnimalRepositoryImpl</b>
     * Передается бин CreateAnimalServiceLmpl и заполняется animalArray
     * @param createAnimalServiceLmpl
     */
    @Autowired
    public AnimalRepositoryImpl(CreateAnimalServiceLmpl createAnimalServiceLmpl) {
        animalArray = createAnimalServiceLmpl.getAnimals();
    }
    /**
     * <b>init</b> запускается после конструктора
     * В данный момент делает ничего
     */
    @PostConstruct
    public void init() {

    }

    /**
     * <b>findLeapYearNames</b> выполняет поиск животных рожденных в високосный год, по массиву животных
     *
     * @return String[] имя животного + дата рождения в формате dd-MM-yyyy
     * @see SearchServiceLmpl findLeapYearNames
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
