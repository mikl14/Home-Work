package ru.mtsbank.fintech.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.mtsbank.fintech.entity.Animal;
import ru.mtsbank.fintech.entity.AnimalType;
import ru.mtsbank.fintech.repositories.AnimalRepository;
import ru.mtsbank.fintech.repositories.AnimalTypeRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    private final AnimalTypeRepository animalTypeRepository;

    public AnimalService(@Lazy AnimalRepository animalRepository, AnimalTypeRepository animalTypeRepository) {
        this.animalRepository = animalRepository;
        this.animalTypeRepository = animalTypeRepository;
    }

    public void initAnimals(AnimalType animalType) {
        List<Animal> animalList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            animalList.add(new Animal("Bob", "bad", BigDecimal.valueOf(200), animalType));
        }
        animalType.setAnimalList(animalList);
        animalRepository.saveAll(animalList);
        animalTypeRepository.save(animalType);
    }

    public List<Animal> getAnimalsById(int id) {
        return animalRepository.findById(id);
    }

    public List<Animal> getAnimalsByName(String name) {
        return animalRepository.findByName(name);
    }

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }
}
