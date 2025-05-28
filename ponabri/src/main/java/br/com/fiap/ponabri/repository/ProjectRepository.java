package br.com.fiap.ponabri.repository;

import br.com.fiap.ponabri.model.Project;
import br.com.fiap.ponabri.model.enums.Status;
import br.com.fiap.ponabri.model.enums.TipoImovel;
import br.com.fiap.ponabri.model.enums.TipoRisco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {
    Optional<Project> findByLogin(String login);

    List<Project> findByStatus(Status status);

    List<Project> findByTipoImovel(TipoImovel tipoImovel);

    List<Project> findByTipoRisco(TipoRisco tipoRisco);

    @Query("SELECT p FROM Project p WHERE p.status = :status AND p.tipoImovel = :tipoImovel")
    List<Project> findByStatusAndTipoImovel(@Param("status") Status status,
                                            @Param("tipoImovel") TipoImovel tipoImovel);

    @Query("SELECT p FROM Project p WHERE p.status = :status AND p.tipoRisco = :tipoRisco")
    List<Project> findByStatusAndTipoRisco(@Param("status") Status status,
                                           @Param("tipoRisco") TipoRisco tipoRisco);
}