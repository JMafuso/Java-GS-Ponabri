package br.com.fiap.ponabri.controller;

import br.com.fiap.ponabri.dto.UsuarioLoginDto;
import br.com.fiap.ponabri.dto.UsuarioRegisterDto;
import br.com.fiap.ponabri.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        usuarioRepository.deleteAll();
    }

    @Test
    public void testRegisterAndLogin() throws Exception {
        UsuarioRegisterDto registerDto = new UsuarioRegisterDto();
        registerDto.setUsername("testuser");
        registerDto.setPassword("password123");
        registerDto.setFullName("Test User");
        registerDto.setEmail("testuser@example.com");

        // Register user
        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.email").value("testuser@example.com"));

        UsuarioLoginDto loginDto = new UsuarioLoginDto();
        loginDto.setUsername("testuser");
        loginDto.setPassword("password123");

        // Login user
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.username").value("testuser"));
    }
}
