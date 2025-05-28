package br.com.fiap.ponabri.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_projeto", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nome"}, name = "uk_nome_projeto"),
        @UniqueConstraint(columnNames = {"login"}, name = "uk_login_projeto")
})
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private String descrição;







}
