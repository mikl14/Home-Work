package ru.mts.animalsCreators;

import org.springframework.stereotype.Component;
import ru.mts.Animals.AbstractAnimal;

@Component
public class CreateAnimalServiceLmpl implements CreateAnimalService {
    private final AnimalFactory animalFactory = new AnimalFactory();

    public AnimalFactory.AnimalType animalType; // хранит тип животного который вернет getAnimal()

    /**
     * <b>getRandomAnimal</b>
     * возвращает случайное животное
     *
     * @return AbstractAnimal
     */
    public AbstractAnimal getRandomAnimal() {
        return animalFactory.getAnimal();
    }

    /**
     * <b>getAnimal</b>
     * возвращает случайное животное заданного типа
     *
     * @return AbstractAnimal
     */
    public AbstractAnimal getAnimal() {
        return animalFactory.getAnimal(animalType);
    }

    /**
     * <b>getAnimals</b>
     *
     * @param numberAnimals количество уникальных животных которых необходимо создать
     * @return Массив животных длинной numberAnimals
     */
    public AbstractAnimal[] getAnimals(int numberAnimals) {

        AbstractAnimal[] animalArray = new AbstractAnimal[numberAnimals];
        for (int i = 0; i < numberAnimals; i++) {
            animalArray[i] = animalFactory.getAnimal();
        }
        return animalArray;
    }

    /**
     * <b>getAnimals()</b>
     * Перегружен в соответствии с т.з
     *
     * @return Массив животных длинной 10
     */
    @Override
    public AbstractAnimal[] getAnimals() {

        int i = 0;
        AbstractAnimal[] animalArray = new AbstractAnimal[10];
        do {
            animalArray[i] = getRandomAnimal();
            i++;
        }
        while (i < 10);

        return animalArray;

    }
}
