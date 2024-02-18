package ru.mtsbank.fintech.animal_repository;

import ru.mts.animals.AbstractAnimal;


public interface AnimalRepository {
    /**
     * <b>findLeapYearNames</b> выполняет поиск животных рожденных в високосный год, по массиву животных
     *
     * @return String[] имя животного + дата рождения в формате dd-MM-yyyy
     */
    String[] findLeapYearNames();

    /**
     * <b>findOlderAnimal</b>
     *
     * @param age искомый возраст
     * @return AbstractAnimal[] - массив зверей olderYears возраста
     */
    AbstractAnimal[] findOlderAnimal(int age);

    /**
     * <b>findDuplicate</b>
     *
     * @return AbstractAnimal[] массив животных имеющих дубликаты в animalArray
     */
    AbstractAnimal[] findDuplicate();
}
