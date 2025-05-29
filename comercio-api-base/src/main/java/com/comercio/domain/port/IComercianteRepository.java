package com.comercio.domain.port;

import com.comercio.domain.model.Comerciante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;

public interface IComercianteRepository {
    Comerciante save(Comerciante comerciante);
    Optional<Comerciante> findById(Long id);
    Page<Comerciante> findByFilters(String razonSocial, String estado, Pageable pageable);
    List<Comerciante> findAllActivos();
    void deleteById(Long id);
    List<String> findDistinctMunicipios();
}
