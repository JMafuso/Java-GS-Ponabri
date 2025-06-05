package br.com.fiap.ponabri.controller;

import br.com.fiap.ponabri.dto.UsuarioLoginDto;
import br.com.fiap.ponabri.dto.UsuarioRegisterDto;
import br.com.fiap.ponabri.dto.UsuarioResponseDto;
import br.com.fiap.ponabri.dto.JwtResponseDto;
import br.com.fiap.ponabri.security.JwtUtils;
import br.com.fiap.ponabri.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, UsuarioService usuarioService, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.usuarioService = usuarioService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDto> register(@Valid @RequestBody UsuarioRegisterDto registerDto) {
        UsuarioResponseDto responseDto = usuarioService.register(registerDto);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@Valid @RequestBody UsuarioLoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails.getUsername());
        return ResponseEntity.ok(new JwtResponseDto(jwt, userDetails.getUsername()));
    }
}
