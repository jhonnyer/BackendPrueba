package com.comercio.service;

import com.comercio.dto.ComercianteRequestDTO;
import com.comercio.dto.ComercianteResponseDTO;
import com.comercio.dto.ComercianteEstadoDTO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IComercianteService {

    ComercianteResponseDTO crear(ComercianteRequestDTO dto, Long usuarioId);
    ComercianteResponseDTO actualizar(Long id, ComercianteRequestDTO dto, Long usuarioId);
    ComercianteResponseDTO obtenerPorId(Long id);
    void eliminar(Long id);
    Page<ComercianteResponseDTO> listar(String razonSocial, String estado, Pageable pageable);
    ComercianteResponseDTO cambiarEstado(Long id, ComercianteEstadoDTO dto, Long usuarioId);
    
    List<String> obtenerMunicipios();
}
