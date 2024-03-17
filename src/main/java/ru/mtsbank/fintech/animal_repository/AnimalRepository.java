package ru.mtsbank.fintech.animal_repository;

import ru.mts.animals.AbstractAnimal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface AnimalRepository {
    /**
     * <b>findLeapYearNames</b> выполняет поиск животных рожденных в високосный год, по массиву животных
     *
     * @return Map<String, LocalDate> ключ: тип + имя животного, значение: дата рождения
     */
    Map<String, LocalDate> findLeapYearNames();

    /**
     * <b>findOlderAnimal</b>
     *
     * @param age искомый возраст
     * @return Map<AbstractAnimal,Integer> - ключ: животное, значение: возраст
     */
    Map<AbstractAnimal,Integer> findOlderAnimal(int age);

    /**
     * <b>findDuplicate</b>
     *
     * @return  Map<String,Integer> ключ: тип животного, значение: количество дубликатов
     */
    Map<String, List<AbstractAnimal>> findDuplicate();
}
