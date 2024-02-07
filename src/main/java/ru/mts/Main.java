package ru.mts;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.mts.animalRepository.AnimalRepositoryImpl;
import ru.mts.animalsCreators.CreateAnimalServiceImpl;

@Configuration
@ComponentScan
public class Main {

    private static void printArray(Object[] animalArray) { //метод для вывода в консоль массива
        System.out.println();
        for (Object animal : animalArray) {
            System.out.println(animal.toString());
        }
    }

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class); //создал контекст
        AnimalRepositoryImpl animalRepositoryBean = context.getBean(AnimalRepositoryImpl.class);

        System.out.println(context.getBean(CreateAnimalServiceImpl.class).getAnimal()); //вывел тип созданного CreateAnimalServiceLmpl для проверки постпроцессора

        printArray(animalRepositoryBean.findLeapYearNames()); //вывожу возвращенные значения всех методов в консоль
        printArray(animalRepositoryBean.findDuplicate());
        printArray(animalRepositoryBean.findOlderAnimal(10));

    }
}