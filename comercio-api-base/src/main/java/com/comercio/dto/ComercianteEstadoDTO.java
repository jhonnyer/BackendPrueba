package com.comercio.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ComercianteEstadoDTO {
    @NotBlank(message = "El nuevo estado es obligatorio")
    private String estado;
}
