package com.comercio.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "USUARIOS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "EMAIL_USER", nullable = false, unique = true)
    private String email;

    @Column(name = "CONTRASENA", nullable = false)
    private String contrasena;

    @Column(name = "ROL", nullable = false)
    private String rol;
}
