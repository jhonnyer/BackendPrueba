package com.comercio.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comercio.domain.model.Usuario;

import java.util.Optional;

public interface IUsuarioRepositoryJpa extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
