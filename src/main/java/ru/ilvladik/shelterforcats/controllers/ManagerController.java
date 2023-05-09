package ru.ilvladik.shelterforcats.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mgr")
public class ManagerController {

    @GetMapping
    public String home() {
        return "mgr/home";
    }
}
