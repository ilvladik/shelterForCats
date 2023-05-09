package ru.ilvladik.shelterforcats.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ilvladik.shelterforcats.models.Cat;
import ru.ilvladik.shelterforcats.services.CatsService;

@Controller
@RequestMapping("/mgr/cats")
public class ManagerCatsController {

    private final CatsService catsService;

    public ManagerCatsController(CatsService catsService) {
        this.catsService = catsService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("cats", catsService.findAllCats());
        return "mgr/cats/index";
    }

    @GetMapping("/new")
    public String newCat(@ModelAttribute("cat") Cat cat) {
        return "/mgr/cats/new";
    }

    @PostMapping
    public String create(@ModelAttribute("cat") @Valid Cat cat,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/mgr/cats/new";
        catsService.save(cat);
        return "redirect:/mgr/cats";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        catsService.delete(id);
        return "redirect:/mgr/cats";
    }


}
