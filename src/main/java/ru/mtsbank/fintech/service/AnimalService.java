package ru.mtsbank.fintech.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.mtsbank.fintech.entity.Animal;
import ru.mtsbank.fintech.repositories.AnimalRepository;

import java.util.List;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;

    public AnimalService(@Lazy AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
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
