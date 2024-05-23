package ru.mtsbank.fintech.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mtsbank.fintech.entity.Animal;
import ru.mtsbank.fintech.entity.AnimalType;
import ru.mtsbank.fintech.repositories.AnimalRepository;
import ru.mtsbank.fintech.service.AnimalService;

import java.util.List;

@RestController
@RequestMapping("/animals/api")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping("/add")
    public String add(@RequestBody Animal animal)
    {
        animalService.addAnimal(animal);
        return "Success";
    }

    @PostMapping("/delete")
    public String delete(@RequestBody Animal animal)
    {
        animalService.deleteAnimal(animal);
        return "delete success";
    }

    @PostMapping("/getAll")
    public List<Animal> getAllAnimals()
    {
        return  animalService.getAllAnimals();
    }
}
