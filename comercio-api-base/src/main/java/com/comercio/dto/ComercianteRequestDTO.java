package com.comercio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ComercianteRequestDTO {

    @NotBlank(message = "La razón social es obligatoria")
    private String razonSocial;

    @NotBlank(message = "El municipio es obligatorio")
    private String municipio;

    private String telefono;

    @Email(message = "Correo electrónico inválido")
    private String correoElectronico;

    @NotNull(message = "La fecha de registro es obligatoria")
    private LocalDate fechaRegistro;

    @NotBlank(message = "El estado es obligatorio (A o I)")
    private String estado;

}
