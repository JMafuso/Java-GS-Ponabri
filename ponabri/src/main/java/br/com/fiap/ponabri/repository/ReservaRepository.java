package br.com.fiap.ponabri.repository;

import br.com.fiap.ponabri.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ReservaRepository extends JpaRepository<Reserva, UUID> {
    Optional<Reserva> findByCodigoReserva(String codigoReserva);
}
