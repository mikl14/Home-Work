package ru.mts.Animals;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *  интерфейс Животных с методами :
 * <b>getBreed</b>,
 * <b>getName</b>,
 * <b>getCost</b>,
 * <b>character</b>.
 */

public interface Animal
{

    /**
     * getBreed - Возвращает значение параметра Breed
     * @see AbstractAnimal#getBreed()
     */
    String getBreed();

    /**
     * getName - Возвращает значение параметра Name
     * @see AbstractAnimal#getName()
     */
    String getName();
    /**
     * getCost - Возвращает значение параметра Cost округленного до 2х знаков
     * @see AbstractAnimal#getCost()
     */
    BigDecimal getCost();
    /**
     * getCharacter - Возвращает значение параметра character
     * @see AbstractAnimal#getCharacter()
     */
    String getCharacter();

    LocalDate getBirthDate();

}
