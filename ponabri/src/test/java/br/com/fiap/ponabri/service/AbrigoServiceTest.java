package br.com.fiap.ponabri.service;

import br.com.fiap.ponabri.model.Abrigo;
import br.com.fiap.ponabri.model.enums.AbrigoStatus;
import br.com.fiap.ponabri.repository.AbrigoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AbrigoServiceTest {

    private AbrigoRepository abrigoRepository;
    private AbrigoAIService abrigoAIService;
    private AbrigoService abrigoService;

    @BeforeEach
    public void setUp() {
        abrigoRepository = mock(AbrigoRepository.class);
        abrigoAIService = mock(AbrigoAIService.class);
        abrigoService = new AbrigoService(abrigoRepository, abrigoAIService);
    }

    @Test
    public void testSaveAbrigo() {
        Abrigo abrigo = new Abrigo();
        abrigo.setDescricao("Abrigo para animais");
        abrigo.setStatus(AbrigoStatus.ATIVO);

        when(abrigoAIService.sugerirCategoria(abrigo.getDescricao())).thenReturn(abrigo.getStatus());
        when(abrigoRepository.save(any(Abrigo.class))).thenAnswer(i -> i.getArgument(0));

        Abrigo saved = abrigoService.save(abrigo);

        assertEquals(abrigo.getStatus(), saved.getCategoriaSugeridaAI());

        verify(abrigoAIService).sugerirCategoria(abrigo.getDescricao());
        verify(abrigoRepository).save(any(Abrigo.class));
    }

    @Test
    public void testFindAll() {
        when(abrigoRepository.findAll()).thenReturn(List.of(new Abrigo(), new Abrigo()));

        List<Abrigo> list = abrigoService.findAll();

        assertEquals(2, list.size());
        verify(abrigoRepository).findAll();
    }

    @Test
    public void testFindById() {
        UUID id = UUID.randomUUID();
        Abrigo abrigo = new Abrigo();
        abrigo.setId(id);

        when(abrigoRepository.findById(id)).thenReturn(Optional.of(abrigo));

        Optional<Abrigo> found = abrigoService.findById(id);

        assertTrue(found.isPresent());
        assertEquals(id, found.get().getId());
        verify(abrigoRepository).findById(id);
    }

    @Test
    public void testDeleteById() {
        UUID id = UUID.randomUUID();

        doNothing().when(abrigoRepository).deleteById(id);

        abrigoService.deleteById(id);

        verify(abrigoRepository).deleteById(id);
    }
}
