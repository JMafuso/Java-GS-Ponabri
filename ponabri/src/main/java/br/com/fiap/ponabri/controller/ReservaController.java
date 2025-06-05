package br.com.fiap.ponabri.controller;

import br.com.fiap.ponabri.model.Reserva;
import br.com.fiap.ponabri.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> getAll() {
        List<Reserva> reservas = reservaService.findAll();
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getById(@PathVariable UUID id) {
        return reservaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Reserva> create(@Valid @RequestBody Reserva reserva) {
        Reserva savedReserva = reservaService.createReserva(reserva);
        return ResponseEntity.ok(savedReserva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> update(@PathVariable UUID id, @Valid @RequestBody Reserva reserva) {
        return reservaService.findById(id)
                .map(existing -> {
                    reserva.setId(id);
                    Reserva updated = reservaService.createReserva(reserva);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        reservaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/VALIDACAO/{codigoReserva}")
    public ResponseEntity<Reserva> validarReserva(@PathVariable String codigoReserva) {
        try {
            Reserva reserva = reservaService.validarReserva(codigoReserva);
            return ResponseEntity.ok(reserva);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
