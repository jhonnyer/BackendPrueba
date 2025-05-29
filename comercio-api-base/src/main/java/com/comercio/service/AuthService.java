package com.comercio.service;

import com.comercio.adapter.repository.IUsuarioRepository;
import com.comercio.domain.model.Usuario;
import com.comercio.dto.AuthRequest;
import com.comercio.dto.AuthResponse;
import com.comercio.security.JwtTokenProvider;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse login(AuthRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getContrasena(), usuario.getContrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtTokenProvider.createToken(
        	    usuario.getEmail(),
        	    usuario.getRol(),
        	    usuario.getId()
        	);
        return new AuthResponse(token);
    }
}
