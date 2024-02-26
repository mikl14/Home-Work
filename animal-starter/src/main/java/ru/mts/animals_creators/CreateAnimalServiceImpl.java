package ru.mts.animals_creators;

import org.springframework.stereotype.Component;
import ru.mts.animals.AbstractAnimal;

import java.util.Locale;

@Component
public class CreateAnimalServiceImpl implements CreateAnimalService {


    private AnimalFactory animalFactory;

    private AnimalFactory.AnimalType animalType; // хранит тип животного который вернет getAnimal()

    public void setAnimalType(AnimalFactory.AnimalType animalType) {
        this.animalType = animalType;
    }

    public AnimalFactory.AnimalType getAnimalType() {
        return animalType;
    }

    public CreateAnimalServiceImpl(AnimalFactory animalFactory) {
        this.animalFactory = animalFactory;
    }

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
        AbstractAnimal animal = animalFactory.getAnimal(animalType);
        String type = animal.getClass().getSimpleName().toUpperCase(Locale.ROOT);
        if (animalType.toString().equals(type)) return animal;
        else throw new IllegalStateException();

    }

    /**
     * <b>getAnimals</b>
     *
     * @param numberAnimals количество уникальных животных которых необходимо создать
     * @return Массив животных длинной numberAnimals
     */
    public AbstractAnimal[] getAnimals(int numberAnimals) {
        if (numberAnimals < 0) throw new IllegalArgumentException();
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
