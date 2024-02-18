package ru.mts.animals_creators;

import ru.mts.animals.AbstractAnimal;

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
    default AbstractAnimal[] getAnimals() {
        int i = 0;
        AbstractAnimal[] animalArray = new AbstractAnimal[10];
        while (i < 10) {
            animalArray[i] = getRandomAnimal();   // получаем случайного зверя через getRandomAnimal()
            i++;
        }
        return animalArray;
    }

}
