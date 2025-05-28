package br.com.fiap.ponabri.service;

import br.com.fiap.ponabri.model.Project;
import br.com.fiap.ponabri.model.enums.Status;
import br.com.fiap.ponabri.model.enums.TipoImovel;
import br.com.fiap.ponabri.model.enums.TipoRisco;
import br.com.fiap.ponabri.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Transactional
    public Project criarProjeto(Project projeto) {
        return projectRepository.save(projeto);
    }

    public Optional<Project> encontrarProjetoPorId(UUID id) {
        return projectRepository.findById(id);
    }

    public List<Project> listarTodosProjetos() {
        return projectRepository.findAll();
    }

    @Transactional
    public Project atualizarProjeto(Project projeto) {
        return projectRepository.save(projeto);
    }

    @Transactional
    public void deletarProjeto(UUID id) {
        projectRepository.deleteById(id);
    }

    public List<Project> encontrarProjetosPorStatus(Status status) {
        return projectRepository.findByStatus(status);
    }

    public List<Project> encontrarProjetosPorTipoImovel(TipoImovel tipoImovel) {
        return projectRepository.findByTipoImovel(tipoImovel);
    }

    public List<Project> encontrarProjetosPorTipoRisco(TipoRisco tipoRisco) {
        return projectRepository.findByTipoRisco(tipoRisco);
    }

    public List<Project> encontrarProjetosAceitosEmCasas() {
        return projectRepository.findByStatusAndTipoImovel(Status.ACEITO, TipoImovel.CASA);
    }

    public List<Project> encontrarProjetosComRiscoDeInundacao() {
        return projectRepository.findByStatusAndTipoRisco(Status.ACEITO, TipoRisco.INUNDACAO);
    }
}
