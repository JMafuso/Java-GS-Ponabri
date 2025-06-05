package br.com.fiap.ponabri.repository;

import br.com.fiap.ponabri.model.Abrigo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AbrigoRepository extends JpaRepository<Abrigo, UUID> {
}
