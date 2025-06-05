package br.com.fiap.ponabri.service;

import br.com.fiap.ponabri.dto.UsuarioRegisterDto;
import br.com.fiap.ponabri.model.Usuario;
import br.com.fiap.ponabri.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        usuarioRepository = mock(UsuarioRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        usuarioService = new UsuarioService(usuarioRepository, passwordEncoder);
    }

    @Test
    public void testRegisterSuccess() {
        UsuarioRegisterDto dto = new UsuarioRegisterDto();
        // dto.setUsername("testuser"); // Removed because username no longer exists
        dto.setPassword("password123");
        dto.setFullName("Test User");
        dto.setEmail("test@example.com");

        // when(usuarioRepository.existsByUsername(dto.getUsername())).thenReturn(false); // Removed
        when(usuarioRepository.existsByEmail(dto.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(dto.getPassword())).thenReturn("hashedpassword");

        ArgumentCaptor<Usuario> captor = ArgumentCaptor.forClass(Usuario.class);
        when(usuarioRepository.save(captor.capture())).thenAnswer(i -> i.getArgument(0));

        var response = usuarioService.register(dto);

        // verify(usuarioRepository).existsByUsername(dto.getUsername()); // Removed
        verify(usuarioRepository).existsByEmail(dto.getEmail());
        verify(passwordEncoder).encode(dto.getPassword());
        verify(usuarioRepository).save(any(Usuario.class));

        Usuario savedUser = captor.getValue();
        // Removed username assertions because Usuario entity no longer has username field
        assertEquals("hashedpassword", savedUser.getPasswordHash());
        assertEquals(dto.getFullName(), savedUser.getFullName());
        assertEquals(dto.getEmail(), savedUser.getEmail());
        assertEquals("ROLE_USER", savedUser.getRole());

        assertEquals(savedUser.getId(), response.getId());
        // Removed username assertions because Usuario entity no longer has username field
        assertEquals(savedUser.getFullName(), response.getFullName());
        assertEquals(savedUser.getEmail(), response.getEmail());
        assertEquals(savedUser.getRole(), response.getRole());
    }

    @Test
    public void testRegisterUsernameExists() {
        UsuarioRegisterDto dto = new UsuarioRegisterDto();
        // dto.setUsername("existinguser"); // Removed
        dto.setEmail("test@example.com");

        when(usuarioRepository.existsByEmail(dto.getEmail())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.register(dto);
        });

        assertEquals("Email already exists", exception.getMessage());
    }

    @Test
    public void testRegisterEmailExists() {
        UsuarioRegisterDto dto = new UsuarioRegisterDto();
        // dto.setUsername("newuser"); // Removed
        dto.setEmail("existing@example.com");

        // when(usuarioRepository.existsByUsername(dto.getUsername())).thenReturn(false); // Removed
        when(usuarioRepository.existsByEmail(dto.getEmail())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.register(dto);
        });

        assertEquals("Email already exists", exception.getMessage());
    }
}
