package ru.mts.animalsCreators;

import ru.mts.Animals.*;

import java.math.BigDecimal;
import java.util.Random;

public class CreateAnimalServiceLmpl implements CreateAnimalService
{
    private Random random = new Random();

    /**
     *  Метод <b>generateRandomName</b>
     * @return строку случайных символов со случайной длинной в диапазоне от 4 до 10
     *  */
    private String generateRandomName()
    {
        var nameBuilder = new StringBuilder();
        for (int j = 0; j < random.nextInt(10 - 4) + 4; j++)
        {
            nameBuilder.append((char) (random.nextInt(10) + 'A'));
        }
        return nameBuilder.toString();
    }
    /**
     *  Метод <b>getRandomAnimal</b>
     * @return случайное животное, генерирует 4 вида животных с уникальными параметрами
     *  */
    public AbstractAnimal getRandomAnimal() {

        String name = generateRandomName();

        switch (random.nextInt(4)) {
            case 0:
                return new Cat("Порода №" + (random.nextInt(1000)),
                        name,
                        "Кошачий",
                        BigDecimal.valueOf(random.nextDouble() * 1000),
                        "Кошачий корм",
                        random.nextInt(12 - 9) + 9);
            case 1:
                return new Fish("Порода №" + (random.nextInt(1000)),
                        name,
                        "Молчунья",
                        BigDecimal.valueOf(random.nextDouble() * 1000),
                        "Рыбий корм",
                        random.nextInt(10 - 5) + 10);
            case 2:
                return new Wolf("Порода №" + (random.nextInt(1000)),
                        name,
                        "Серый",
                        BigDecimal.valueOf(random.nextDouble() * 1000),
                        "Лес",
                        random.nextInt(150 - 80) + 80);
            case 3:
                return new Bear("Порода №" + (random.nextInt(1000)),
                        name,
                        "Горящий",
                        BigDecimal.valueOf(random.nextDouble() * 1000),
                        "Тайга",
                        random.nextInt(80 - 40) + 40);
            default:
                return null;
        }

    }

    /**
     *
     * @param numberAnimals количество уникальных животных которых необходимо создать
     * @return Массив животных длинной numberAnimals
     */

    public AbstractAnimal[] getAnimals(int numberAnimals) {

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
