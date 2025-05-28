package br.com.fiap.ponabri.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table (name = "tb_projeto")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @NotBlank
    private String nome;

    private String nome_responsavel_abrigo;

    private String logradouro_abrigo;

    private String bairro_abrigo;

    private String cidade_abrigo;

    private String estado_abrigo;

    private String login;

    private String senha;

    private double qt_pessoas;

    private double capacidade_total;

    private boolean status_disponivel;
    
    private String descrição;







}
