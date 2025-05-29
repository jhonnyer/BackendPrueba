package com.comercio.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ComercianteCsvDTO {
    private String razonSocial;
    private String municipio;
    private String telefono;
    private String email;
    private LocalDate fechaRegistro;
    private String estado;
    private Integer cantidadEstablecimientos;
    private BigDecimal totalIngresos;
    private Integer cantidadEmpleados;
}
