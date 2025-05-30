package com.comercio.domain.port;

import com.comercio.domain.model.Usuario;

import java.util.Optional;

public interface IUsuarioRepository {
    Optional<Usuario> findByEmail(String email);
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(Long id);
}
