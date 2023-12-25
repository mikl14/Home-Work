package ru.mts.animalsCreators;

import ru.mts.Animals.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

public class CreateAnimalServiceLmpl implements CreateAnimalService
{
    private final AnimalFactory animalFactory = new AnimalFactory();

    /**
     * <b>getRandomAnimal</b>
     * возвращает случайное животное
     * @return
     */
    public AbstractAnimal getRandomAnimal() {
      return animalFactory.getAnimal();
    }

    /**
     *<b>getAnimals</b>
     * @param numberAnimals количество уникальных животных которых необходимо создать
     * @return Массив животных длинной numberAnimals
     */

    public AbstractAnimal[] getAnimals(int numberAnimals) {

        AbstractAnimal[] animalArray = new AbstractAnimal[numberAnimals];
        for(int i = 0; i < numberAnimals;i++)
        {
            animalArray[i] = animalFactory.getAnimal();
        }
        return animalArray;
    }

    /**
     * Перегружен в соответствии с т.з
     * @return Массив животных длинной 10
     */

    @Override
    public AbstractAnimal[] getAnimals() {

        int i = 0;
        AbstractAnimal[] animalArray = new AbstractAnimal[10];
        do
        {
            animalArray[i] = getRandomAnimal();
            i++;
        }
        while(i < 10);

        return animalArray;

    }
}
