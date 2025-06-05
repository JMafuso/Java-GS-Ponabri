package br.com.fiap.ponabri.service;

import br.com.fiap.ponabri.model.Abrigo;
import br.com.fiap.ponabri.model.enums.AbrigoStatus;
import br.com.fiap.ponabri.repository.AbrigoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AbrigoService {

    private final AbrigoRepository abrigoRepository;
    private final AbrigoAIService abrigoAIService;

    public AbrigoService(AbrigoRepository abrigoRepository, AbrigoAIService abrigoAIService) {
        this.abrigoRepository = abrigoRepository;
        this.abrigoAIService = abrigoAIService;
    }

    public List<Abrigo> findAll() {
        return abrigoRepository.findAll();
    }

    public Optional<Abrigo> findById(UUID id) {
        return abrigoRepository.findById(id);
    }

    @Transactional
    public Abrigo save(Abrigo abrigo) {
        // Initialize vagasDisponiveis if not set
        if (abrigo.getVagasDisponiveis() == 0) {
            abrigo.setVagasDisponiveis(abrigo.getCapacidadeTotal());
        }
        // Call AI service to suggest category
        AbrigoStatus categoriaSugerida = abrigoAIService.sugerirCategoria(abrigo.getDescricao());
        abrigo.setCategoriaSugeridaAI(categoriaSugerida);
        return abrigoRepository.save(abrigo);
    }

    @Transactional
    public void deleteById(UUID id) {
        abrigoRepository.deleteById(id);
    }
}
