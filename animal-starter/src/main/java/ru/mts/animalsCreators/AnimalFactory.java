package ru.mts.animalsCreators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mts.Animals.*;
import ru.mts.config.animalStarterProperties;

import java.util.Random;

@Component
public class AnimalFactory {
    private Random random = new Random();
    @Autowired
    private animalStarterProperties properties;


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
                return new Cat(properties.getCatNames()[random.nextInt(properties.getCatNames().length)], "Кошачий", "Кошачий корм");
            case FISH:
                return new Fish(properties.getFishNames()[random.nextInt(properties.getFishNames().length)], "Молчунья", "Рыбий корм");
            case WOLF:
                return new Wolf(properties.getWolfNames()[random.nextInt(properties.getWolfNames().length)], "Серый", "Лес");
            case BEAR:
                return new Bear(properties.getBearNames()[random.nextInt(properties.getBearNames().length)], "Горящий", "Тайга");
            default:
                return null;
        }
    }
}