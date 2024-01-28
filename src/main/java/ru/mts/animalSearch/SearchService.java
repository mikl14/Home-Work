package ru.mts.animalSearch;

import ru.mts.Animals.AbstractAnimal;

public interface SearchService {
    /**
     * <b>findLeapYearNames</b> выполняет поиск животных рожденных в високосный год, по массиву животных
     *
     * @param animalArray массив животных для поиска
     * @return String[] имя животного + дата рождения в формате dd-MM-yyyy
     * @see SearchServiceLmpl findLeapYearNames
     */
    String[] findLeapYearNames(AbstractAnimal[] animalArray);

    /**
     * <b>findOlderAnimal</b>
     *
     * @param animalArray массив животных для поиска
     * @param olderYears  искомый возраст
     * @return AbstractAnimal[] - массив зверей olderYears возраста
     * @see SearchServiceLmpl findOlderAnimal
     */
    AbstractAnimal[] findOlderAnimal(AbstractAnimal[] animalArray, int olderYears);

    /**
     * <b>findDuplicate</b>
     *
     * @param animalArray массив животных для поиска
     * @return AbstractAnimal[] массив животных имеющих дубликаты в animalArray
     * @see SearchServiceLmpl findDuplicate
     */
    AbstractAnimal[] findDuplicate(AbstractAnimal[] animalArray);
}
