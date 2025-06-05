package br.com.fiap.ponabri.controller;

import br.com.fiap.ponabri.model.Abrigo;
import br.com.fiap.ponabri.service.AbrigoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/abrigos")
public class AbrigoController {

    private final AbrigoService abrigoService;

    public AbrigoController(AbrigoService abrigoService) {
        this.abrigoService = abrigoService;
    }

    @GetMapping
    public ResponseEntity<List<Abrigo>> getAll() {
        List<Abrigo> abrigos = abrigoService.findAll();
        return ResponseEntity.ok(abrigos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Abrigo> getById(@PathVariable UUID id) {
        return abrigoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Abrigo> create(@Valid @RequestBody Abrigo abrigo) {
        Abrigo savedAbrigo = abrigoService.save(abrigo);
        return ResponseEntity.ok(savedAbrigo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Abrigo> update(@PathVariable UUID id, @Valid @RequestBody Abrigo abrigo) {
        return abrigoService.findById(id)
                .map(existing -> {
                    abrigo.setId(id);
                    Abrigo updated = abrigoService.save(abrigo);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        abrigoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
