package ru.mts.animalsCreators;

import ru.mts.Animals.*;

import java.math.BigDecimal;
import java.util.Random;

public interface CreateAnimalService
{

    /**
     *  Метод <b>getNumberOfAnimals</b> будет переопределен в классе <b>CreateAnimalServiceLmpl</b>
     * @param numberAnimals количество уникальных животных которых необходимо создать
     * @see CreateAnimalServiceLmpl#getNumberOfAnimals(int)
     *  */
    AbstractAnimal[] getNumberOfAnimals(int numberAnimals);


    /**
     *  Метод <b>getRandomAnimal</b>
     * @return случайное животное, генерирует 4 вида животных с уникальными параметрами
     *  */
default AbstractAnimal getRandomAnimal() {
        var random = new Random();
        var name = generateRandomName(random);
        return generateRandomAnimal(random, name);
    }

    private String generateRandomName(Random random) {
        var nameBuilder = new StringBuilder();
        for (int j = 0; j < random.nextInt(10 - 4) + 4; j++) {
            nameBuilder.append((char) (random.nextInt(10) + 'A'));
        }
        return nameBuilder.toString();
    }

    private AbstractAnimal generateRandomAnimal(Random randomise, String name) {
        switch (randomise.nextInt(4)) {
            case 0:
                return new Cat("Порода №" + (randomise.nextInt(1000)),
                        name,
                        "Кошачий",
                        BigDecimal.valueOf(randomise.nextDouble() * 1000),
                        "Кошачий корм",
                        randomise.nextInt(12 - 9) + 9);
            case 1:
                return new Fish("Порода №" + (randomise.nextInt(1000)),
                        name,
                        "Молчунья",
                        BigDecimal.valueOf(randomise.nextDouble() * 1000),
                        "Рыбий корм",
                        randomise.nextInt(10 - 5) + 10);
            case 2:
                return new Wolf("Порода №" + (randomise.nextInt(1000)),
                        name,
                        "Серый",
                        BigDecimal.valueOf(randomise.nextDouble() * 1000),
                        "Лес",
                        randomise.nextInt(150 - 80) + 80);
            case 3:
                return new Bear("Порода №" + (randomise.nextInt(1000)),
                        name,
                        "Горящий",
                        BigDecimal.valueOf(randomise.nextDouble() * 1000),
                        "Тайга",
                        randomise.nextInt(80 - 40) + 40);
            default:
                return null;
        }
    }
    {
        Random randomise = new Random();

        StringBuilder nameBuilder = new StringBuilder();

        for (int j = 0; j < randomise.nextInt(10-4)+4; j++) // создаем случайное имя путем накопления char в nameBuilder, длинна имени случайно определяется в диапазоне от 4 до 10
        {
            nameBuilder.append((char) (randomise.nextInt(10) + 'A')); //прибавляем А чтобы всегда оказываться по кодировке в месте с заглавными буквами, ограничился только 10 заглавными буквами алфавита
        }

        String name = nameBuilder.toString();

        switch(randomise.nextInt(4)) // выбираем случайное животное из 4х
        {
            /*
                Порода - строка содержащая уникальный номер доселе неизвестной породы животных, образована случайно в диапазоне от 0 до 1000
                Цена - Взята как случайный double и умножена на 1000 чтобы цены не были равны сотым долям

                Продолжительность жизни (для наследников Pet) - Случайное число в диапазоне зависящему от диапазона средней продолжительности жизни животных (его я нагуглил)
                Вес (для наследников Predator) - Случайное число в диапазоне зависящему от диапазона веса животных (его я придумал)
             */
            case 0:
               return new Cat("Порода №"+(randomise.nextInt(1000)),name,"Кошачий", BigDecimal.valueOf(randomise.nextDouble() * 1000),"Кошачий корм", randomise.nextInt(12-9)+9);
            case 1:
                return new Fish("Порода №"+(randomise.nextInt(1000)),name,"Молчунья", BigDecimal.valueOf(randomise.nextDouble()* 1000),"Рыбий корм", randomise.nextInt(10-5)+10);
            case 2:
                return new Wolf("Порода №"+(randomise.nextInt(1000)),name,"Серый", BigDecimal.valueOf(randomise.nextDouble()* 1000),"Лес", randomise.nextInt(150-80)+80);
            case 3:
                return new Bear("Порода №"+(randomise.nextInt(1000)),name,"Горящий", BigDecimal.valueOf(randomise.nextDouble()* 1000),"Тайга", randomise.nextInt(80-40)+40);
            default:
                return null;
        }

    }

    /**
     * Метод <b>getAnimals</b>
     * Был Перегружен в <b>CreateAnimalServiceLmpl<b> по т.з
     * @see CreateAnimalServiceLmpl#getAnimals()
     * @return Массив из 10 случайных животных
     */
    default AbstractAnimal[] getAnimals()
    {
        int i = 0;
        AbstractAnimal[] animalArray = new AbstractAnimal[10];
        while(i < 10)
        {
            animalArray[i] = getRandomAnimal();   // получаем случайного зверя через getRandomAnimal()
            i++;
        }
        return animalArray;
    }
}
