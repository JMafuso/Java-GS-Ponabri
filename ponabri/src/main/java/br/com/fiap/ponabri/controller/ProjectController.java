package br.com.fiap.ponabri.controller;

import br.com.fiap.ponabri.model.Project;
import br.com.fiap.ponabri.model.enums.Status;
import br.com.fiap.ponabri.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projetos")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<Project> criarProjeto(@RequestBody Project projeto) {
        Project projetoCriado = projectService.criarProjeto(projeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(projetoCriado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> encontrarProjetoPorId(@PathVariable UUID id) {
        return projectService.encontrarProjetoPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Project>> encontrarProjetosPorStatus(@PathVariable Status status) {
        List<Project> projetos = projectService.encontrarProjetosPorStatus(status);
        return ResponseEntity.ok(projetos);
    }
}