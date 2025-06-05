package br.com.fiap.ponabri.service;

import br.com.fiap.ponabri.model.enums.AbrigoStatus;
import org.springframework.stereotype.Service;

@Service
public class AbrigoAIService {

    public AbrigoStatus sugerirCategoria(String descricao) {
        // Simple AI simulation: categorize based on keywords in description
        if (descricao == null || descricao.isEmpty()) {
            return AbrigoStatus.INATIVO;
        }
        String descLower = descricao.toLowerCase();
        if (descLower.contains("emergencia") || descLower.contains("urgente")) {
            return AbrigoStatus.ATIVO;
        }
        if (descLower.contains("manutencao") || descLower.contains("reforma")) {
            return AbrigoStatus.EM_MANUTENCAO;
        }
        return AbrigoStatus.ATIVO;
    }
}
