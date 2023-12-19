package ru.mts;

import ru.mts.Animals.AbstractAnimal;
import ru.mts.animalsCreators.CreateAnimalServiceLmpl;

public class Main {
    public static void main(String[] args) {

        int n = 4;
        CreateAnimalServiceLmpl createAnimalServiceLmpl = new CreateAnimalServiceLmpl();

        AbstractAnimal[] animals;

        System.out.println("Случайные 10 животных: ");

        animals = createAnimalServiceLmpl.getAnimals();
        for(AbstractAnimal animal:animals)
        {
            System.out.println(animal.toString());
        }

        System.out.println("Случайные "+n+" животных: ");

        animals = createAnimalServiceLmpl.getNumberOfAnimals(n);
        for(AbstractAnimal animal:animals)
        {
            System.out.println(animal.toString());
        }
    }
}