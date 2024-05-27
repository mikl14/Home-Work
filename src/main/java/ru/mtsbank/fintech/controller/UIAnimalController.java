package ru.mtsbank.fintech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mtsbank.fintech.entity.Animal;
import ru.mtsbank.fintech.service.AnimalService;
import ru.mtsbank.fintech.service.UserService;

@Controller
public class UIAnimalController {

    private final AnimalService animalService;

    private final UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UIAnimalController(AnimalService animalService,UserService userService) {
        this.animalService = animalService;
        this.userService = userService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("animalList", animalService.getAllAnimals());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("animal", animalService.findAnimalbyId(id));
        return "edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        animalService.deleteAnimal(id);
        return "redirect:/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("animal", new Animal());
        return "add";
    }

    @PostMapping(value = "/add", params = "action=add")
    public String addAnimal(Model model, Animal animal) {
        animalService.addAnimal(animal);
        return "redirect:/index";
    }

    @PostMapping(value = "/add", params = "action=cancel")
    public String cancel(Model model) {
        return "redirect:/index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping(value = "/login", params = "action=signIn")
    public String login(Model model,String username,String password) {
       // userService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/index";
    }
}
