package br.com.fiap.ponabri.service;

import br.com.fiap.ponabri.dto.ProjectDTO;
import br.com.fiap.ponabri.model.Project;
import br.com.fiap.ponabri.model.enums.Status;
import br.com.fiap.ponabri.model.enums.TipoImovel;
import br.com.fiap.ponabri.model.enums.TipoRisco;
import br.com.fiap.ponabri.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectDTO salvar (ProjectDTO projectDTO) {
        Project project = toEntity(projectDTO);
        if (projectDTO.getUuid() == null) {
            project = projectRepository.save(project);
        } else {
            Optional<Project> existente = projectRepository.findById(projectDTO.getUuid());
            if (existente.isPresent()) {
                existente.get().setNome(project.getNome());
                existente.get().setNome_responsavel_abrigo(project.getNome_responsavel_abrigo());
                existente.get().setLogradouro_abrigo(project.getLogradouro_abrigo());
                existente.get().setBairro_abrigo(project.getBairro_abrigo());
                existente.get().setCidade_abrigo(project.getCidade_abrigo());
                existente.get().setEstado_abrigo(project.getEstado_abrigo());
                existente.get().setLogin(project.getLogin());
                existente.get().setSenha(project.getSenha());
                existente.get().setQt_pessoas(project.getQt_pessoas());
                existente.get().setCapacidade_total(project.getCapacidade_total());
                existente.get().setStatus_disponivel(project.getStatus_disponivel());
                existente.get().setDescricao(project.getDescricao());
                existente.get().setTipoImovel(project.getTipoImovel());
                existente.get().setTipoRisco(project.getTipoRisco());
                existente.get().setStatus(project.getStatus());
                project = projectRepository.save(existente.get());
            }
        }
        return toDto(project);
    }
    public List<ProjectDTO> findAll() {
        List<Project> lista = projectRepository.findAll();
        return lista.stream().map(this::toDto).toList();
    }
    public void deletarPorID(UUID uuid) { projectRepository.deleteById(uuid);}

    public ProjectDTO findById(UUID uuid) {
        Optional<Project> optional = projectRepository.findById(uuid);
        return optional.map(this::toDto).orElseThrow(() -> new RuntimeException("Regstro não encontrado"));
    }
    private Project toEntity(ProjectDTO dto) {
        Project entity = new Project();
        entity.setUuid(dto.getUuid());
        entity.setNome(dto.getNome());
        entity.setNome_responsavel_abrigo(dto.getNome_responsavel_abrigo());
        entity.setLogradouro_abrigo(dto.getLogradouro_abrigo());
        entity.setBairro_abrigo(dto.getBairro_abrigo());
        entity.setCidade_abrigo(dto.getCidade_abrigo());
        entity.setEstado_abrigo(dto.getEstado_abrigo());
        entity.setLogin(dto.getLogin());
        entity.setSenha(dto.getSenha());
        entity.setQt_pessoas(dto.getQt_pessoas());
        entity.setCapacidade_total(dto.getCapacidade_total());
        entity.setStatus_disponivel(dto.getStatus_disponivel());
        entity.setDescricao(dto.getDescricao());
        entity.setTipoImovel(dto.getTipoImovel());
        entity.setTipoRisco(dto.getTipoRisco());
        entity.setStatus(dto.getStatus());
        return entity;
    }
    private ProjectDTO toDto(Project entity) {
        ProjectDTO dto = new ProjectDTO();
        dto.setUuid(entity.getUuid());
        dto.setNome(entity.getNome());
        dto.setNome_responsavel_abrigo(entity.getNome_responsavel_abrigo());
        dto.setLogradouro_abrigo(entity.getLogradouro_abrigo());
        dto.setBairro_abrigo(entity.getBairro_abrigo());
        dto.setCidade_abrigo(entity.getCidade_abrigo());
        dto.setEstado_abrigo(entity.getEstado_abrigo());
        dto.setLogin(entity.getLogin());
        dto.setSenha(entity.getSenha());
        dto.setQt_pessoas(entity.getQt_pessoas());
        dto.setCapacidade_total(entity.getCapacidade_total());
        dto.setStatus_disponivel(entity.getStatus_disponivel());
        dto.setDescricao(entity.getDescricao());
        dto.setTipoImovel(entity.getTipoImovel());
        dto.setTipoRisco(entity.getTipoRisco());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}