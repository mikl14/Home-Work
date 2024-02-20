package ru.mtsbank.fintech;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.mtsbank.fintech.animal_repository.AnimalRepositoryImpl;
import ru.mtsbank.fintech.starter_tests.test_config.TestsConfiguration;

@ActiveProfiles("test")
@SpringBootTest

@Import(TestsConfiguration.class)
class FintechApplicationTests {
	@Autowired
    AnimalRepositoryImpl animalRepository;
	@Test
	void contextLoads() {
		Assertions.assertNotNull(animalRepository);
	}

	/**
	 * <b>animalRepositoryArrayTest</b>
	 * проверяет что после инициализации массив оказался заполнен
	 */
	@Test
	void animalRepositoryArrayTest()
	{
		Assertions.assertNotEquals(animalRepository.getAnimalArray().length,0);
	}

	/**
	 * <b>findOlderAnimalExceptionTest</b>
	 * - Тестирование метода поиска животных по возрасту
	 * Ожидаемый результат: IllegalArgumentException так как передан отрицательный возраст
	 */
	@Test
	void findOlderAnimalExceptionTest()
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> animalRepository.findOlderAnimal(-12));
	}

}
