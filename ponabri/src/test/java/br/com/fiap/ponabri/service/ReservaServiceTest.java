package br.com.fiap.ponabri.service;

import br.com.fiap.ponabri.model.Reserva;
import br.com.fiap.ponabri.repository.ReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReservaServiceTest {

    private ReservaRepository reservaRepository;
    private ReservaService reservaService;

    @BeforeEach
    public void setUp() {
        reservaRepository = mock(ReservaRepository.class);
        reservaService = new ReservaService(reservaRepository, null, null);
    }

    @Test
    public void testFindAll() {
        when(reservaRepository.findAll()).thenReturn(List.of(new Reserva(), new Reserva()));

        List<Reserva> list = reservaService.findAll();

        assertEquals(2, list.size());
        verify(reservaRepository).findAll();
    }

    @Test
    public void testFindById() {
        UUID id = UUID.randomUUID();
        Reserva reserva = new Reserva();
        reserva.setId(id);

        when(reservaRepository.findById(id)).thenReturn(Optional.of(reserva));

        Optional<Reserva> found = reservaService.findById(id);

        assertTrue(found.isPresent());
        assertEquals(id, found.get().getId());
        verify(reservaRepository).findById(id);
    }

    @Test
    public void testDeleteById() {
        UUID id = UUID.randomUUID();

        doNothing().when(reservaRepository).deleteById(id);

        reservaService.deleteById(id);

        verify(reservaRepository).deleteById(id);
    }
}
