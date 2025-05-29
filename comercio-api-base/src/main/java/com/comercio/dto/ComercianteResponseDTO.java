package com.comercio.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ComercianteResponseDTO {

    private Long id;
    private String razonSocial;
    private String municipio;
    private String telefono;
    private String correoElectronico;
    private LocalDate fechaRegistro;
    private String estado;
    private LocalDate fechaActualizacion;
    private Long usuarioActualizacion;

}
