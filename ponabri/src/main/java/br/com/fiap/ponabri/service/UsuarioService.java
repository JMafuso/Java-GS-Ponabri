package br.com.fiap.ponabri.service;

import br.com.fiap.ponabri.dto.UsuarioRegisterDto;
import br.com.fiap.ponabri.dto.UsuarioResponseDto;
import br.com.fiap.ponabri.model.Usuario;
import br.com.fiap.ponabri.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UsuarioResponseDto register(UsuarioRegisterDto registerDto) {
        if (usuarioRepository.existsByEmail(registerDto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        Usuario usuario = new Usuario();
        usuario.setPasswordHash(passwordEncoder.encode(registerDto.getPassword()));
        usuario.setFullName(registerDto.getFullName());
        usuario.setEmail(registerDto.getEmail());
        usuario.setRole("ROLE_USER");
        usuario = usuarioRepository.save(usuario);
        return toResponseDto(usuario);
    }

    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public UsuarioResponseDto toResponseDto(Usuario usuario) {
        UsuarioResponseDto dto = new UsuarioResponseDto();
        dto.setId(usuario.getId());
        dto.setFullName(usuario.getFullName());
        dto.setEmail(usuario.getEmail());
        dto.setRole(usuario.getRole());
        return dto;
    }
}
