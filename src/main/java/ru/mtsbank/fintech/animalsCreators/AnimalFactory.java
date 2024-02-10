package ru.mtsbank.fintech.animalsCreators;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import ru.mtsbank.fintech.Animals.*;

import java.util.Random;

@Component
public class AnimalFactory {
    private Random random = new Random();

    @Value("${animal.catNames}")
    private String[] catNames;

    @Value("${animal.fishNames}")
    private String[] fishNames;

    @Value("${animal.wolfNames}")
    private String[] wolfNames;
    @Value("${animal.bearNames}")
    private String[] bearNames;

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
                return new Cat(catNames[random.nextInt(catNames.length)],"Кошачий", "Кошачий корм");
            case FISH:
                return new Fish(fishNames[random.nextInt(catNames.length)],"Молчунья", "Рыбий корм");
            case WOLF:
                return new Wolf(wolfNames[random.nextInt(catNames.length)],"Серый", "Лес");
            case BEAR:
                return new Bear(bearNames[random.nextInt(catNames.length)],"Горящий", "Тайга");
            default:
                return null;
        }
    }
}
