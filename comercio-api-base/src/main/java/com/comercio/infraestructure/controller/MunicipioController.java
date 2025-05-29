package com.comercio.infraestructure.controller;

import com.comercio.dto.ApiResponse;
import com.comercio.service.IComercianteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/municipios")
@PreAuthorize("hasAnyRole('ADMINISTRADOR', 'AUXILIAR_REGISTRO')")
public class MunicipioController {

    private final IComercianteService comercianteService;

    public MunicipioController(IComercianteService comercianteService) {
        this.comercianteService = comercianteService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> listarMunicipios() {
        List<String> municipios = comercianteService.obtenerMunicipios();
        return ResponseEntity.ok(ApiResponse.success(municipios));
    }
}
