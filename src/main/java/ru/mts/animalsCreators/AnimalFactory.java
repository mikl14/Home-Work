package ru.mts.animalsCreators;

import ru.mts.Animals.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

public class AnimalFactory {
    private final Random random = new Random();

    public enum AnimalType {
        CAT,
        BEAR,
        WOLF,
        FISH
    }

    /**
     *  Метод <b>generateRandomDate</b>
     * @return LocalDate
     *  */
    private LocalDate generateRandomDate()
    {
        Long epoch = (long) (random.nextDouble() * (LocalDate.now().toEpochDay()));
        return LocalDate.ofEpochDay(epoch);
    }

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
     * <b>getAnimal()</b> перегрузка,
     * вызывает getAnimal(AnimalType type) передавая в него случайный тип
     * @return AbstractAnimal
     */

    public AbstractAnimal getAnimal()
    {
        return getAnimal(AnimalType.values()[random.nextInt(AnimalType.values().length)]); // Возвращаем массив всех типов и берем от него значение со случайным индексом
    }

    /**
     * <b>AbstractAnimal</b> - возвращает животное заданного типа
     * @param type
     * @return AbstractAnimal
     */
    public AbstractAnimal getAnimal(AnimalType type) {
        String name = generateRandomName();

        switch (type) {
            case CAT:
                return new Cat("Порода №" + (random.nextInt(1000)),
                        name,
                        "Кошачий",
                        generateRandomDate(),
                        BigDecimal.valueOf(random.nextDouble() * 1000),
                        "Кошачий корм",
                        random.nextInt(12 - 9) + 9);
            case FISH:
                return new Fish("Порода №" + (random.nextInt(1000)),
                        name,
                        "Молчунья",
                        generateRandomDate(),
                        BigDecimal.valueOf(random.nextDouble() * 1000),
                        "Рыбий корм",
                        random.nextInt(10 - 5) + 10);
            case WOLF:
                return new Wolf("Порода №" + (random.nextInt(1000)),
                        name,
                        "Серый",
                        generateRandomDate(),
                        BigDecimal.valueOf(random.nextDouble() * 1000),
                        "Лес",
                        random.nextInt(150 - 80) + 80);
            case BEAR:
                return new Bear("Порода №" + (random.nextInt(1000)),
                        name,
                        "Горящий",
                        generateRandomDate(),
                        BigDecimal.valueOf(random.nextDouble() * 1000),
                        "Тайга",
                        random.nextInt(80 - 40) + 40);
            default:
                return null;
        }
    }
}
