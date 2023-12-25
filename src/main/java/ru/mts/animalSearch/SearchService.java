package ru.mts.animalSearch;

import ru.mts.Animals.AbstractAnimal;

public interface SearchService
{
    /**
     * <b>findLeapYearNames</b> выполняет поиск животных рожденных в високосный год, по массиву животных
     * @param animalArray массив животных для поиска
     * @see SearchServiceLmpl findLeapYearNames
     * @return String[] имя животного + дата рождения в формате dd-MM-yyyy
     */
    String[] findLeapYearNames(AbstractAnimal[] animalArray);

    /**
     *<b>findOlderAnimal</b>
     * @param animalArray массив животных для поиска
     * @param olderYears искомый возраст
     * @see SearchServiceLmpl findOlderAnimal
     * @return AbstractAnimal[] - массив зверей olderYears возраста
     */
    AbstractAnimal[] findOlderAnimal(AbstractAnimal[] animalArray,int olderYears);

    /**
     * <b>findDuplicate</b>
     * @param animalArray массив животных для поиска
     * @see SearchServiceLmpl findDuplicate
     * @return AbstractAnimal[] массив животных имеющих дубликаты в animalArray
     */
    AbstractAnimal[] findDuplicate(AbstractAnimal[] animalArray);
}
