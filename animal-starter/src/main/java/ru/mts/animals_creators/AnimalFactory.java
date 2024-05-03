package ru.mts.animals_creators;

import org.springframework.stereotype.Component;
import ru.mts.animals.*;
import ru.mts.config.AnimalStarterProperties;

import javax.swing.*;
import java.util.Random;

@Component
public class AnimalFactory {
    private Random random = new Random();
    private AnimalStarterProperties properties;

    public AnimalFactory(AnimalStarterProperties properties) {
        this.properties = properties;
    }

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
                return new AbstractAnimal("CAT",properties.getCatNames()[random.nextInt(properties.getFishNames().length)], "Kitten");
            case FISH:
                return new AbstractAnimal("FISH",properties.getFishNames()[random.nextInt(properties.getFishNames().length)], "Small");
            case WOLF:
                return new AbstractAnimal("WOLF",properties.getWolfNames()[random.nextInt(properties.getWolfNames().length)], "Grey");
            case BEAR:
                return new AbstractAnimal("BEAR",properties.getBearNames()[random.nextInt(properties.getBearNames().length)], "Hot");
            default:
                throw new IllegalArgumentException();
        }
    }
}
