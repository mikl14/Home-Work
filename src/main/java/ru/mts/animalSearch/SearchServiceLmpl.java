package ru.mts.animalSearch;

import ru.mts.Animals.AbstractAnimal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SearchServiceLmpl implements SearchService
{
    @Override
    public String[] findLeapYearNames(AbstractAnimal[] animalArray) {

        List<String> leapYearBirthAnimal = new ArrayList<>();
        for(AbstractAnimal animal : animalArray)
        {
            if(animal.getBirthDate().isLeapYear())
            {
                leapYearBirthAnimal.add(animal.getName()+" " + animal.getBirthDate());
            }
        }
        String[] leapYearBirthAnimalArray = leapYearBirthAnimal.toArray(new String[0]);
        return leapYearBirthAnimalArray;
    }

    @Override
    public AbstractAnimal[] findOlderAnimal(AbstractAnimal[] animalArray,int olderYears) {

        List<AbstractAnimal> olderAnimal = new ArrayList<>();

        for(int i = 0 ; i < animalArray.length;i++)
        {
            long ss = LocalDate.now().toEpochDay()- animalArray[i].getBirthDate().toEpochDay();

            if(LocalDate.ofEpochDay(ss).getYear()- 1970 == olderYears)
            {
                olderAnimal.add(animalArray[i]);
            }
        }
        AbstractAnimal[] olderAnimalArray = olderAnimal.toArray(new AbstractAnimal[0]);

        return olderAnimalArray;
    }

    @Override
    public AbstractAnimal[] findDuplicate(AbstractAnimal[] animalArray) {

        List<AbstractAnimal> equalAnimal = new ArrayList<>();

        for(int i = 0; i <animalArray.length;i++)
        {
            for(int j = 0; j < animalArray.length;j++)
            {
                if( i != j && animalArray[i].equals(animalArray[j]))
                {
                    equalAnimal.add(animalArray[i]);
                }
            }
        }

        AbstractAnimal[] equalAnimalArray = equalAnimal.toArray(new AbstractAnimal[0]);
        return equalAnimalArray;
    }
}
