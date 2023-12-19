package ru.mts.animalsCreators;

import ru.mts.Animals.AbstractAnimal;

public class CreateAnimalServiceLmpl implements CreateAnimalService
{
    /**
     *
     * @param numberAnimals количество уникальных животных которых необходимо создать
     * @return Массив животных длинной numberAnimals
     *
     * использует дефолтный метод <b>getRandomAnimal()</b> из интерфейса <b>CreateAnimalService</b>
     * @see CreateAnimalService#getRandomAnimal()
     */
    @Override
    public AbstractAnimal[] getNumberOfAnimals(int numberAnimals) {

        AbstractAnimal[] animalArray = new AbstractAnimal[numberAnimals];
        for(int i = 0; i < numberAnimals;i++)
        {
            animalArray[i] = getRandomAnimal();
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
