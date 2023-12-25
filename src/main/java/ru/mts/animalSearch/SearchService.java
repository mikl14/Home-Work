package ru.mts.animalSearch;

import ru.mts.Animals.AbstractAnimal;

public interface SearchService
{
    String[] findLeapYearNames(AbstractAnimal[] animalArray);

    AbstractAnimal[] findOlderAnimal(AbstractAnimal[] animalArray,int olderYears);

    AbstractAnimal[] findDuplicate(AbstractAnimal[] animalArray);
}
