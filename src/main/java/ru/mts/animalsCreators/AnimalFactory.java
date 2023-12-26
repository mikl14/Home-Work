package ru.mts.animalsCreators;

import ru.mts.Animals.*;

import java.util.Random;

public class AnimalFactory {
    private Random random = new Random();

    public enum AnimalType {
        CAT, BEAR, WOLF, FISH
    }

    /**
     * <b>getAnimal()</b> перегрузка,
     * вызывает getAnimal(AnimalType type) передавая в него случайный тип
     *
     * @return AbstractAnimal
     */
    public AbstractAnimal getAnimal() {
        return getAnimal(AnimalType.values()[random.nextInt(AnimalType.values().length)]); // Возвращаем массив всех типов и берем от него значение со случайным индексом
    }

    /**
     * <b>AbstractAnimal</b> - возвращает животное заданного типа
     *
     * @param type
     * @return AbstractAnimal
     */
    public AbstractAnimal getAnimal(AnimalType type) {

        switch (type) {
            case CAT:
                return new Cat("Кошачий", "Кошачий корм");
            case FISH:
                return new Fish("Молчунья", "Рыбий корм");
            case WOLF:
                return new Wolf("Серый", "Лес");
            case BEAR:
                return new Bear("Горящий", "Тайга");
            default:
                return null;
        }
    }
}
