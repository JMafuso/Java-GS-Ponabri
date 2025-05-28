package br.com.fiap.ponabri.controller;

import br.com.fiap.ponabri.dto.ProjectDTO;
import br.com.fiap.ponabri.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public String listar(Model model) {
        List<ProjectDTO> lista = projectService.findAll();
        model.addAttribute("project", lista);
        return "project/lista";
    }

    @GetMapping("/novo")
    public String lista(Model model){
        model.addAttribute("project", new ProjectDTO());
        return "project/formulario";
    }


    @PostMapping
    public String salvar(@Valid @ModelAttribute("project") ProjectDTO projectDTO,
                         BindingResult bindingResults, Model model) {
        if (bindingResults.hasErrors()) {
            return "project/formulario";
        }
        projectService.salvar(projectDTO);
        return "redirect:/project";
    }

    @GetMapping("/editar/{uuid}")
    public String editar(@PathVariable UUID uuid, Model model) {
        ProjectDTO projectDTO = projectService.findById(uuid);
        model.addAttribute("project", projectDTO);
        return "project/formulario";
    }

    @GetMapping("/deletar/{uuid}")
    public String deletar(@PathVariable UUID uuid) {
        projectService.deletarPorID(uuid);
        return "redirect:/project";
    }
}