package ru.mtsbank.fintech.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.mtsbank.fintech.entity.Animal;
import ru.mtsbank.fintech.repositories.AnimalRepository;
import ru.mtsbank.fintech.repositories.AnimalTypeRepository;

import java.util.List;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final AnimalTypeRepository animalTypeRepository;

    public AnimalService(@Lazy AnimalRepository animalRepository, AnimalTypeRepository animalTypeRepository) {
        this.animalRepository = animalRepository;
        this.animalTypeRepository = animalTypeRepository;
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

    public void addAnimal(Animal animal) {
        animalRepository.save(animal);
    }

    public void deleteAnimal(Animal animal) {
        animalRepository.delete(animal);
    }

    public void deleteAnimal(int id) {
        animalRepository.delete(animalRepository.findById(id).get(0));
    }

    public Animal findAnimalbyId(int id) {
        return animalRepository.findById(id).get(0);
    }
}
