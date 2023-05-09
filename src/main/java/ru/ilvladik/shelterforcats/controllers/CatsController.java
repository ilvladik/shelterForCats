package ru.ilvladik.shelterforcats.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.ilvladik.shelterforcats.models.Application;
import ru.ilvladik.shelterforcats.models.Cat;
import ru.ilvladik.shelterforcats.models.Picture;
import ru.ilvladik.shelterforcats.services.ApplicationsService;
import ru.ilvladik.shelterforcats.services.CatsService;
import ru.ilvladik.shelterforcats.utils.ApplicationValidator;

@Controller
@RequestMapping("/cats")
public class CatsController {

    private final CatsService catsService;
    private final ApplicationsService applicationsService;
    private final ApplicationValidator applicationValidator;

    @Autowired
    public CatsController(CatsService catsService,
                          ApplicationsService applicationsService,
                          ApplicationValidator applicationValidator) {
        this.catsService = catsService;
        this.applicationsService = applicationsService;
        this.applicationValidator = applicationValidator;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("cats", catsService.findAllCats());
        return "cats/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id,
                       @ModelAttribute Application application,
                       Model model) {
        Cat cat = catsService.findCat(id);
        if (cat == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        model.addAttribute("cat", cat);
        System.out.println(cat);
        return "cats/show";
    }

    @PostMapping("/{id}")
    public String create(@PathVariable int id,
                         @ModelAttribute Application application,
                         Model model,
                         BindingResult bindingResult) {
        Cat cat = catsService.findCat(id);
        System.out.println(cat);
        if (cat == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        System.out.println(cat);
        model.addAttribute("cat", cat);
        applicationValidator.validate(application, bindingResult);

        System.out.println(cat);
        if (bindingResult.hasErrors()) {
            System.out.println(cat);
            return "cats/show";
        }

        application.setCat(cat);
        applicationsService.save(application);
        System.out.println(cat);
        return "cats/success";
    }

    @GetMapping("/{id}/picture")
    public ResponseEntity<byte[]> picture(@PathVariable int id) {
        Picture picture = catsService.getPictureByCatId(id);
        if (picture == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"cat" + id + "\"")
                .contentType(MediaType.valueOf(picture.getContentType()))
                .body(picture.getData());
    }
}
