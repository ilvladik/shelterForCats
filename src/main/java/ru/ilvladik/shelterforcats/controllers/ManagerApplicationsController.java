package ru.ilvladik.shelterforcats.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ilvladik.shelterforcats.services.ApplicationsService;

@Controller
@RequestMapping("/mgr/apps")
public class ManagerApplicationsController {

    private final ApplicationsService applicationsService;

    public ManagerApplicationsController(ApplicationsService applicationsService) {
        this.applicationsService = applicationsService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("apps", applicationsService.getAllApplications());
        return "mgr/apps/index";
    }

    @DeleteMapping("/{id}")
    public String deleteApp(@PathVariable int id) {
        applicationsService.delete(id);
        return "redirect:/mgr/apps";
    }
}
