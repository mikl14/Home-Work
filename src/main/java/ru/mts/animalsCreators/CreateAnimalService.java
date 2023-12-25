package ru.mts.animalsCreators;

import ru.mts.Animals.AbstractAnimal;

public interface CreateAnimalService
{
    /**
     * Метод <b>getAnimals</b>
     * Определен <b>CreateAnimalServiceLmpl<b>
     * @see CreateAnimalServiceLmpl#getRandomAnimal()
     */
    AbstractAnimal getRandomAnimal();

    /**
     * Метод <b>getAnimals</b>
     * Был Перегружен в <b>CreateAnimalServiceLmpl<b> по т.з
     * @see CreateAnimalServiceLmpl#getAnimals()
     * @return Массив из 10 случайных животных
     */
    default AbstractAnimal[] getAnimals()
    {
        int i = 0;
        AbstractAnimal[] animalArray = new AbstractAnimal[10];
        while(i < 10)
        {
            animalArray[i] = getRandomAnimal();   // получаем случайного зверя через getRandomAnimal()
            i++;
        }
        return animalArray;
    }

}
