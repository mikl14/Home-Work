package ru.mts.animals_creators;

import ru.mts.animals.AbstractAnimal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CreateAnimalService {

    /**
     * Метод <b>getAnimals</b>
     * Определен <b>CreateAnimalServiceLmpl<b>
     *
     * @see CreateAnimalServiceImpl#getRandomAnimal()
     */
    AbstractAnimal getRandomAnimal();

    /**
     * Метод <b>getAnimals</b>
     * Был Перегружен в <b>CreateAnimalServiceLmpl<b> по т.з
     *
     * @return Массив из 10 случайных животных
     * @see CreateAnimalServiceImpl#getAnimals()
     */
    default Map<String, List<AbstractAnimal>> getAnimals() {
        int i = 0;
        Map<String, List<AbstractAnimal>> animalMap = new HashMap<>();
        while (i < 10) {
            AbstractAnimal animal = getRandomAnimal();
            animalMap.get(animal.getAnimalType()).add(animal);
            i++;
        }
        return animalMap;
    }

}
