package com.comercio.service;

import com.comercio.domain.model.Usuario;
import com.comercio.adapter.repository.IUsuarioRepository;
import com.comercio.dto.AuthRequest;
import com.comercio.dto.AuthResponse;
import com.comercio.security.JwtTokenProvider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private IUsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_ShouldReturnToken_WhenCredentialsAreValid() {
        String email = "admin@comercio.com";
        String rawPassword = "123456";
        String hashedPassword = "hashedPassword";
        String token = "jwtToken";

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setContrasena(hashedPassword);
        usuario.setRol("Administrador");
        usuario.setId(1L);

        AuthRequest request = new AuthRequest();
        request.setEmail(email);
        request.setContrasena(rawPassword);

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches(rawPassword, hashedPassword)).thenReturn(true);
        when(jwtTokenProvider.createToken(email, "Administrador", 1L)).thenReturn(token);

        // Act
        AuthResponse response = authService.login(request);

        // Assert
        assertNotNull(response);
        assertEquals(token, response.getToken());
    }

    @Test
    void login_ShouldThrowException_WhenUserNotFound() {
        AuthRequest request = new AuthRequest();
        request.setEmail("no@found.com");
        request.setContrasena("123456");

        when(usuarioRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> authService.login(request));
        assertEquals("Usuario no encontrado", exception.getMessage());
    }

    @Test
    void login_ShouldThrowException_WhenPasswordInvalid() {
        String email = "admin@comercio.com";

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setContrasena("hashed");

        AuthRequest request = new AuthRequest();
        request.setEmail(email);
        request.setContrasena("wrong");

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("wrong", "hashed")).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> authService.login(request));
        assertEquals("Contraseña incorrecta", exception.getMessage());
    }
}