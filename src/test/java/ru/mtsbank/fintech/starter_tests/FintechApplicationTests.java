package ru.mtsbank.fintech.starter_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mtsbank.fintech.animal_repository.AnimalRepositoryImpl;

@SpringBootTest
class FintechApplicationTests {
	@Autowired
    AnimalRepositoryImpl animalRepository;
	@Test
	void contextLoads() {
		Assertions.assertNotNull(animalRepository);
	}

	@Test
	void animalRepositoryArrayTest()
	{
		Assertions.assertNotEquals(animalRepository.getAnimalArray().length,0);
	}

	@Test
	void findOlderAnimalExceptionTest()
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> animalRepository.findOlderAnimal(-12));
	}

}
