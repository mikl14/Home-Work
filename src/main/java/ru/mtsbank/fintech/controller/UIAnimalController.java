package ru.mtsbank.fintech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mtsbank.fintech.entity.Animal;
import ru.mtsbank.fintech.service.AnimalService;

@Controller
public class UIAnimalController {

    private final AnimalService animalService;


    public UIAnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/index")
    public String index(Model model)
    {
        model.addAttribute("animalList", animalService.getAllAnimals());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("animal", animalService.findAnimalbyId(id));
        return "edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model)
    {
        animalService.deleteAnimal(id);
        return "redirect:/index";
    }
    @GetMapping("/add")
    public String add(Model model)
    {
        model.addAttribute("animal", new Animal());
        return "add";
    }

    @PostMapping(value = "/add",params = "action=add")
    public String addAnimal(Model model, Animal animal)
    {
        animal.setId(animalService.getAllAnimals().size()+1);
        animalService.addAnimal(animal);
        return  "redirect:/index";
    }

    @PostMapping(value = "/add",params = "action=cancel")
    public String cancel(Model model)
    {
        return  "redirect:/index";
    }
}
