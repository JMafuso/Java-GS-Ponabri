package br.com.fiap.ponabri.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectController {

    @GetMapping("/")
    public String home() {
        return "index"; // This will render src/main/resources/templates/index.html
    }
}
