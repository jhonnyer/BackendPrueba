package com.comercio.adapter.repository;

import com.comercio.domain.model.Usuario;
import com.comercio.domain.port.IUsuarioRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsuarioRepositoryJpaImpl implements IUsuarioRepository {

    private final IUsuarioRepositoryJpa repositoryJpa;

    public UsuarioRepositoryJpaImpl(IUsuarioRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return repositoryJpa.findByEmail(email);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return repositoryJpa.save(usuario);
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return repositoryJpa.findById(id);
    }
}
