package ru.mtsbank.fintech;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mtsbank.fintech.animalRepository.AnimalRepositoryImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {
@Autowired
private AnimalRepositoryImpl animalRepository;
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	private static String printArray (Object[] animalArray) { //метод для вывода в консоль массива
		StringBuffer sb = new StringBuffer();
		sb.append('\n');
		for (Object animal : animalArray) {
			sb.append(animal.toString() + '\n');
		}
		return sb.toString();
	}

	@Scheduled(fixedRate = 1000 * 60)
	public void animalScheduledLog() {
		log.info("FindOlder animal {}",printArray(animalRepository.findOlderAnimal(10)));

		log.info("FindLeapYearNames animal {}",printArray(animalRepository.findLeapYearNames()));

		log.info("The time is now {}",printArray(animalRepository.findDuplicate()));
	}
}