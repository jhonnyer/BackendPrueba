package com.comercio.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String contrasena;
}
