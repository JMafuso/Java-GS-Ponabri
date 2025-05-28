package br.com.fiap.ponabri.dto;

import br.com.fiap.ponabri.model.enums.Status;
import br.com.fiap.ponabri.model.enums.TipoImovel;
import br.com.fiap.ponabri.model.enums.TipoRisco;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private UUID uuid;

    @Column(nullable = false)
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100)
    private String nome;

    @Column(nullable = false)
    @NotBlank(message = "Nome do responsável pelo abrigo é obrigatório")
    @Size(max = 100)
    private String nome_responsavel_abrigo;

    @Column(nullable = false)
    @NotBlank(message = "Logradouro do abrigo é obrigatório")
    @Size(max = 200)
    private String logradouro_abrigo;

    @Column(nullable = false)
    @NotBlank(message = "Bairro do abrigo é obrigatório")
    @Size(max = 50)
    private String bairro_abrigo;

    @Column(nullable = false)
    @NotBlank(message = "Cidade do abrigo é obrigatório")
    @Size(max = 50)
    private String cidade_abrigo;

    @Column(nullable = false)
    @NotBlank(message = "Estado do abrigo é obrigatório")
    @Size(max = 2)
    private String estado_abrigo;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Login é obrigatório")
    @Size(min = 4, max = 30)
    private String login;

    @Column(nullable = false)
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, max = 60)
    private String senha;

    @Column(nullable = false)
    @NotNull(message = "Quantidade de pessoas é obrigatória")
    private Double qt_pessoas;

    @Column(nullable = false)
    @NotNull(message = "Capacidade total é obrigatória")
    private Double capacidade_total;

    @Column(nullable = false)
    @NotNull(message = "Status de disponibilidade é obrigatório")
    private Boolean status_disponivel;

    @NotBlank
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoImovel tipoImovel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoRisco tipoRisco;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}
