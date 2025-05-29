package com.comercio.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "COMERCIANTES")
@Getter
@Setter
public class Comerciante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COMERCIANTE")
    private Long id;

    @Column(name = "RAZON_SOCIAL", nullable = false)
    private String razonSocial;

    @Column(name = "MUNICIPIO", nullable = false)
    private String municipio;

    @Column(name = "TELEFONO")
    private String telefono;

    @Column(name = "EMAIL_COMERCIANTE")
    private String correoElectronico;

    @Column(name = "FECHA_REGISTRO", nullable = false)
    private LocalDate fechaRegistro;

    @Column(name = "ESTADO", nullable = false)
    private String estado; // A = Activo, I = Inactivo

    @Column(name = "FECHA_ACTUALIZACION")
    private LocalDate fechaActualizacion;

    @Column(name = "USUARIO_ACTUALIZACION")
    private Long usuarioActualizacion;
}
