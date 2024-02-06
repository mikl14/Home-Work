package ru.mts.animalSearch;

import ru.mts.Animals.AbstractAnimal;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchServiceLmpl implements SearchService {
    /**
     * <b>findLeapYearNames</b> выполняет поиск животных рожденных в високосный год, по массиву животных
     *
     * @param animalArray массив животных для поиска
     * @return String[] имя животного + дата рождения в формате dd-MM-yyyy
     */
    @Override
    public String[] findLeapYearNames(AbstractAnimal[] animalArray) {

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
     * @param animalArray массив животных для поиска
     * @param olderYears  искомый возраст
     * @return AbstractAnimal[] - массив зверей большего чем olderYears возраста
     */
    @Override
    public AbstractAnimal[] findOlderAnimal(AbstractAnimal[] animalArray, int olderYears) {

        if (olderYears < 0) throw new IllegalArgumentException();
        List<AbstractAnimal> olderAnimal = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        for (AbstractAnimal animal : animalArray) {
            int age = Period.between(animal.getBirthDate(), currentDate).getYears();
            if (age > olderYears) {
                olderAnimal.add(animal);
            }

        }
        return olderAnimal.toArray(new AbstractAnimal[0]);
    }

    /**
     * <b>findDuplicate</b>
     *
     * @param animalArray массив животных для поиска
     * @return AbstractAnimal[] массив животных имеющих дубликаты в animalArray
     */
    @Override
    public AbstractAnimal[] findDuplicate(AbstractAnimal[] animalArray) {

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
