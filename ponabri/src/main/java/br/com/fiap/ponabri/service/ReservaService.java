package br.com.fiap.ponabri.service;

import br.com.fiap.ponabri.model.Abrigo;
import br.com.fiap.ponabri.model.Reserva;
import br.com.fiap.ponabri.model.enums.ReservaStatus;
import br.com.fiap.ponabri.repository.AbrigoRepository;
import br.com.fiap.ponabri.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.UUID;
import java.util.Random;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final AbrigoRepository abrigoRepository;
    private final RabbitMQProducerService rabbitMQProducerService;

    public ReservaService(ReservaRepository reservaRepository, AbrigoRepository abrigoRepository, RabbitMQProducerService rabbitMQProducerService) {
        this.reservaRepository = reservaRepository;
        this.abrigoRepository = abrigoRepository;
        this.rabbitMQProducerService = rabbitMQProducerService;
    }

    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> findById(UUID id) {
        return reservaRepository.findById(id);
    }

    @Transactional
    public Reserva createReserva(Reserva reserva) {
        Abrigo abrigo = abrigoRepository.findById(reserva.getAbrigo().getId())
                .orElseThrow(() -> new RuntimeException("Abrigo não encontrado"));

        if (abrigo.getVagasDisponiveis() <= 0) {
            throw new RuntimeException("Não há vagas disponíveis no abrigo");
        }

        abrigo.setVagasDisponiveis(abrigo.getVagasDisponiveis() - 1);
        abrigoRepository.save(abrigo);

        reserva.setStatus(ReservaStatus.ATIVA);
        reserva.setCodigoReserva(generateUniqueCodigoReserva());
        Reserva savedReserva = reservaRepository.save(reserva);

        // Publish message to RabbitMQ
        rabbitMQProducerService.sendMessage("Nova reserva criada: " + savedReserva.getCodigoReserva());

        return savedReserva;
    }

    @Transactional
    public void deleteById(UUID id) {
        reservaRepository.deleteById(id);
    }

    public Optional<Reserva> findByCodigoReserva(String codigoReserva) {
        return reservaRepository.findByCodigoReserva(codigoReserva);
    }

    @Transactional
    public Reserva validarReserva(String codigoReserva) {
        Reserva reserva = reservaRepository.findByCodigoReserva(codigoReserva)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        reserva.setStatus(ReservaStatus.VALIDADA);
        return reservaRepository.save(reserva);
    }

    private String generateUniqueCodigoReserva() {
        String codigo;
        do {
            codigo = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        } while (reservaRepository.findByCodigoReserva(codigo).isPresent());
        return codigo;
    }
}
