package br.com.fiap.ponabri.service;

import br.com.fiap.ponabri.dto.ProjectDTO;
import br.com.fiap.ponabri.model.Project;
import br.com.fiap.ponabri.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById_ProjectExists() {
        Project project = new Project();
        UUID projectId = UUID.randomUUID();
        project.setUuid(projectId);
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project)); // Mock repository returns Optional<Project>

        ProjectDTO result = projectService.findById(projectId);

        assertNotNull(result);
        assertEquals(projectId, result.getUuid());
        verify(projectRepository, times(1)).findById(projectId);
    }

    @Test
    public void testFindById_ProjectDoesNotExist() {
        UUID projectId = UUID.randomUUID();
        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());

        try {
            projectService.findById(projectId);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            assertEquals("Regstro não encontrado", e.getMessage());
        }
        verify(projectRepository, times(1)).findById(projectId);
    }
}
