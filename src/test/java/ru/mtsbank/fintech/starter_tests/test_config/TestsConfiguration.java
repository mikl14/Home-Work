package ru.mtsbank.fintech.starter_tests.test_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import ru.mts.animals.AbstractAnimal;
import ru.mts.animals.Bear;
import ru.mts.animals.Cat;
import ru.mts.animals.Fish;
import ru.mts.animals_creators.CreateAnimalServiceImpl;
import ru.mtsbank.fintech.animal_repository.AnimalRepositoryImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

@org.springframework.boot.test.context.TestConfiguration

public class TestsConfiguration {
    @Scope("singleton")
    @Bean
    AnimalRepositoryImpl animalRepository(CreateAnimalServiceImpl createAnimalService) {
        return new AnimalRepositoryImpl(createAnimalService){
            @Override
            public void init() {
                this.setAnimalArray(new AbstractAnimal[] {	new Fish("Abis", "Pan", "Evil", LocalDate.of(2012, 12, 1), BigDecimal.valueOf(123), "meat", 12),
                        new Bear("White", "Beluga", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "forest", 120),
                        new Fish("Not abris", "Not Pan", "Evil", LocalDate.of(2008, 12, 1), BigDecimal.valueOf(123), "meat", 12),
                        new Cat("Great", "Piter", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12),
                        new Fish("Great", "Piter Pan", "Evil", LocalDate.of(2012, 11, 1), BigDecimal.valueOf(123), "meat", 12),
                        new Cat("Abis", "Pan", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12),
                        new Cat("Abis", "Pan", "Evil", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12),
                        new Cat("Abis", "Pan", "Good", LocalDate.of(2015, 12, 1), BigDecimal.valueOf(123), "meat", 12)});
            }

        };
    }



}
