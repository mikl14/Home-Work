package ru.mts;

import ru.mts.Animals.AbstractAnimal;
import ru.mts.Animals.Cat;
import ru.mts.animalSearch.SearchServiceLmpl;
import ru.mts.animalsCreators.CreateAnimalServiceLmpl;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        CreateAnimalServiceLmpl createAnimalServiceLmpl = new CreateAnimalServiceLmpl();


        SearchServiceLmpl searchServiceLmpl = new SearchServiceLmpl();

        AbstractAnimal[] duplicateArray = new AbstractAnimal[]{
                new Cat("Abis","Pan","Evil", LocalDate.of(2015,12,1),BigDecimal.valueOf(123), "meat",12),
                new Cat("Not abris","Not Pan","Evil", LocalDate.of(2015,12,1),BigDecimal.valueOf(123), "meat",12),
                new Cat("Great","Piter","Evil", LocalDate.of(2015,12,1),BigDecimal.valueOf(123), "meat",12),
                new Cat("Great","Piter Pan","Evil", LocalDate.of(2015,12,1),BigDecimal.valueOf(123), "meat",12),
                new Cat("Abis","Pan","Evil", LocalDate.of(2015,12,1),BigDecimal.valueOf(123), "meat",12)};
        duplicateArray =  searchServiceLmpl.findDuplicate(duplicateArray);


        System.out.println("\nДубликаты животных: ");
        for(AbstractAnimal animal:duplicateArray)
        {
            System.out.println(animal.toString());
        }

        AbstractAnimal[] leapYearsAnimals = createAnimalServiceLmpl.getAnimals(20);
        String[] leapYearsAnimalsNames = searchServiceLmpl.findLeapYearNames(leapYearsAnimals);

        System.out.println("\nЖивотные рожденные в високосный год: ");
        for(String animal:leapYearsAnimalsNames)
        {
            System.out.println(animal);
        }

        AbstractAnimal[] olderYearsAnimals = createAnimalServiceLmpl.getAnimals(1000);
        olderYearsAnimals = searchServiceLmpl.findOlderAnimal(olderYearsAnimals,10);

        System.out.println("\nЖивотные возрастом 10 лет: ");
        for(AbstractAnimal animal:olderYearsAnimals)
        {
            System.out.println(animal.getName() +" "+ animal.getBirthDate());
        }

    }
}