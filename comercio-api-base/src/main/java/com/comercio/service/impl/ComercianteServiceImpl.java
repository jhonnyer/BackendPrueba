package com.comercio.service.impl;

import com.comercio.service.IComercianteService;
import com.comercio.domain.model.Comerciante;
import com.comercio.domain.port.IComercianteRepository;
import com.comercio.dto.ComercianteRequestDTO;
import com.comercio.dto.ComercianteResponseDTO;
import com.comercio.exception.ResourceNotFoundException;
import com.comercio.dto.ComercianteEstadoDTO;
import com.comercio.mapper.ComercianteMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ComercianteServiceImpl implements IComercianteService {

    private final IComercianteRepository repository;

    @Autowired
    public ComercianteServiceImpl(IComercianteRepository repository) {
        this.repository = repository;
    }

    @Override
    public ComercianteResponseDTO crear(ComercianteRequestDTO dto, Long usuarioId) {
        Comerciante comerciante = ComercianteMapper.toEntity(dto);
        comerciante.setFechaActualizacion(LocalDate.now());
        comerciante.setUsuarioActualizacion(usuarioId);
        return ComercianteMapper.toDTO(repository.save(comerciante));
    }

    @Override
    public ComercianteResponseDTO actualizar(Long id, ComercianteRequestDTO dto, Long usuarioId) {
        Comerciante existente = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Comerciante no encontrado"));

        existente.setRazonSocial(dto.getRazonSocial());
        existente.setMunicipio(dto.getMunicipio());
        existente.setTelefono(dto.getTelefono());
        existente.setCorreoElectronico(dto.getCorreoElectronico());
        existente.setFechaRegistro(dto.getFechaRegistro());
        existente.setEstado(dto.getEstado());
        existente.setFechaActualizacion(LocalDate.now());
        existente.setUsuarioActualizacion(usuarioId);

        return ComercianteMapper.toDTO(repository.save(existente));
    }

    @Override
    public ComercianteResponseDTO obtenerPorId(Long id) {
        Comerciante comerciante = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Comerciante no encontrado"));
        return ComercianteMapper.toDTO(comerciante);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<ComercianteResponseDTO> listar(String razonSocial, String estado, Pageable pageable) {
        return repository.findByFilters(razonSocial, estado, pageable)
                .map(ComercianteMapper::toDTO);
    }

    @Override
    public ComercianteResponseDTO cambiarEstado(Long id, ComercianteEstadoDTO dto, Long usuarioId) {
        Comerciante comerciante = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Comerciante no encontrado"));
        comerciante.setEstado(dto.getEstado());
        comerciante.setFechaActualizacion(LocalDate.now());
        comerciante.setUsuarioActualizacion(usuarioId);
        return ComercianteMapper.toDTO(repository.save(comerciante));
    }
    
    @Override
    @Cacheable("municipios")
    public List<String> obtenerMunicipios() {
    	System.out.println("Consultando municipios desde BD");
        return repository.findDistinctMunicipios();
    }

    
}
