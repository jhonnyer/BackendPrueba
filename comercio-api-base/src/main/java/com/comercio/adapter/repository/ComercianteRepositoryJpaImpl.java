package com.comercio.adapter.repository;

import com.comercio.domain.model.Comerciante;
import com.comercio.domain.port.IComercianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ComercianteRepositoryJpaImpl implements IComercianteRepository {

    @Autowired
    private IComercianteRepositoryJpa repository;

    @Override
    public Comerciante save(Comerciante comerciante) {
        return repository.save(comerciante);
    }

    @Override
    public Optional<Comerciante> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<Comerciante> findByFilters(String razonSocial, String estado, Pageable pageable) {
        return repository.findByFilters(razonSocial, estado, pageable);
    }

    @Override
    public List<Comerciante> findAllActivos() {
        return repository.findByEstado("A");
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
    @Override
    public List<String> findDistinctMunicipios() {
        return repository.findDistinctMunicipios();
    }

}
