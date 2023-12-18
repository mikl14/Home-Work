package ru.mts;

import ru.mts.animalsCreators.CreateAnimalServiceLmpl;

public class Main {
    public static void main(String[] args) {

        int N = 4;
        CreateAnimalServiceLmpl createAnimalServiceLmpl = new CreateAnimalServiceLmpl();

        System.out.println("Случайные 10 животных: ");
        createAnimalServiceLmpl.getAnimals();
        System.out.println("Случайные "+N+" животных: ");
        createAnimalServiceLmpl.getNumberOfAnimals(N);


    }
}