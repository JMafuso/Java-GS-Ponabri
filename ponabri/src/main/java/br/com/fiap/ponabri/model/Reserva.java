package br.com.fiap.ponabri.model;

import br.com.fiap.ponabri.model.enums.ReservaStatus;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "RESERVAS")
public class Reserva {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String codigoReserva;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "abrigo_id")
    private Abrigo abrigo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservaStatus status;

    // Getters and setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Abrigo getAbrigo() {
        return abrigo;
    }

    public void setAbrigo(Abrigo abrigo) {
        this.abrigo = abrigo;
    }

    public ReservaStatus getStatus() {
        return status;
    }

    public void setStatus(ReservaStatus status) {
        this.status = status;
    }
}
