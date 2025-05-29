package com.comercio.mapper;

import com.comercio.dto.ComercianteRequestDTO;
import com.comercio.dto.ComercianteResponseDTO;
import com.comercio.domain.model.Comerciante;

public class ComercianteMapper {

    public static Comerciante toEntity(ComercianteRequestDTO dto) {
        Comerciante c = new Comerciante();
        c.setRazonSocial(dto.getRazonSocial());
        c.setMunicipio(dto.getMunicipio());
        c.setTelefono(dto.getTelefono());
        c.setCorreoElectronico(dto.getCorreoElectronico());
        c.setFechaRegistro(dto.getFechaRegistro());
        c.setEstado(dto.getEstado());
        return c;
    }

    public static ComercianteResponseDTO toDTO(Comerciante c) {
        ComercianteResponseDTO dto = new ComercianteResponseDTO();
        dto.setId(c.getId());
        dto.setRazonSocial(c.getRazonSocial());
        dto.setMunicipio(c.getMunicipio());
        dto.setTelefono(c.getTelefono());
        dto.setCorreoElectronico(c.getCorreoElectronico());
        dto.setFechaRegistro(c.getFechaRegistro());
        dto.setEstado(c.getEstado());
        dto.setFechaActualizacion(c.getFechaActualizacion());
        dto.setUsuarioActualizacion(c.getUsuarioActualizacion());
        return dto;
    }
}
