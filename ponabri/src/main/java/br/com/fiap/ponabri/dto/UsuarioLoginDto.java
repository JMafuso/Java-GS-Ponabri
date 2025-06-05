package br.com.fiap.ponabri.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioLoginDto {

    @NotBlank(message = "{validation.username.notblank}")
    private String username;

    @NotBlank(message = "{validation.password.notblank}")
    @Size(min = 6, max = 100)
    private String password;

    // Getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
