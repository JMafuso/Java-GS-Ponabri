package br.com.fiap.ponabri.model;

import br.com.fiap.ponabri.model.enums.AbrigoStatus;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ABRIGOS")
public class Abrigo {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_sugerida_ai")
    private AbrigoStatus categoriaSugeridaAI;

    @Column(nullable = false)
    private int capacidadeTotal;

    @Column(nullable = false)
    private int vagasDisponiveis;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AbrigoStatus status;

    // Getters and setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public AbrigoStatus getCategoriaSugeridaAI() {
        return categoriaSugeridaAI;
    }

    public void setCategoriaSugeridaAI(AbrigoStatus categoriaSugeridaAI) {
        this.categoriaSugeridaAI = categoriaSugeridaAI;
    }

    public int getCapacidadeTotal() {
        return capacidadeTotal;
    }

    public void setCapacidadeTotal(int capacidadeTotal) {
        this.capacidadeTotal = capacidadeTotal;
    }

    public int getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    public void setVagasDisponiveis(int vagasDisponiveis) {
        this.vagasDisponiveis = vagasDisponiveis;
    }

    public AbrigoStatus getStatus() {
        return status;
    }

    public void setStatus(AbrigoStatus status) {
        this.status = status;
    }
}
