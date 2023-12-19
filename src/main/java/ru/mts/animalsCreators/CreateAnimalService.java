package ru.mts.animalsCreators;

import ru.mts.Animals.*;

import java.math.BigDecimal;
import java.util.Random;

public interface CreateAnimalService
{
    /**
     * Метод <b>getAnimals</b>
     * Был Перегружен в <b>CreateAnimalServiceLmpl<b> по т.з
     * @see CreateAnimalServiceLmpl#getAnimals()
     * @return Массив из 10 случайных животных
     */
    default AbstractAnimal[] getAnimals()
    {
        CreateAnimalServiceLmpl createAnimalServiceLmpl = new CreateAnimalServiceLmpl();
        int i = 0;
        AbstractAnimal[] animalArray = new AbstractAnimal[10];
        while(i < 10)
        {
            animalArray[i] = createAnimalServiceLmpl.getRandomAnimal();   // получаем случайного зверя через getRandomAnimal()
            i++;
        }
        return animalArray;
    }
}
