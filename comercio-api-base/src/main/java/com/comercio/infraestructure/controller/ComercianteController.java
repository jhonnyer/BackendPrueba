package com.comercio.infraestructure.controller;

import com.comercio.service.IComercianteService;
import com.comercio.dto.ComercianteRequestDTO;
import com.comercio.dto.ComercianteResponseDTO;
import com.comercio.dto.ComercianteEstadoDTO;
import com.comercio.exception.ResourceNotFoundException;
import com.comercio.util.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comerciantes")
@PreAuthorize("hasAnyRole('Administrador', 'Auxiliar_Registro')")
public class ComercianteController {

    @Autowired
    private IComercianteService service;

    @Autowired
    private HttpServletRequest request;

    @PostMapping
    public ResponseEntity<ApiResponse> crear(@Valid @RequestBody ComercianteRequestDTO dto) {
        try {
            Long usuarioId = (Long) request.getAttribute("usuarioId");
            ComercianteResponseDTO creado = service.crear(dto, usuarioId);
            return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(creado));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al crear comerciante: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> actualizar(@PathVariable Long id, @Valid @RequestBody ComercianteRequestDTO dto) {
        try {
            Long usuarioId = (Long) request.getAttribute("usuarioId");
            ComercianteResponseDTO actualizado = service.actualizar(id, dto, usuarioId);
            return ResponseEntity.ok(ApiResponse.success(actualizado));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al actualizar comerciante: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> consultarPorId(@PathVariable Long id) {
        try {
            ComercianteResponseDTO dto = service.obtenerPorId(id);
            return ResponseEntity.ok(ApiResponse.success(dto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> listar(
            @RequestParam(defaultValue = "") String razonSocial,
            @RequestParam(defaultValue = "") String estado,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        try {
            Page<ComercianteResponseDTO> pagina = service.listar(razonSocial, estado, PageRequest.of(page, size));
            return ResponseEntity.ok(ApiResponse.success(pagina));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al listar comerciantes: " + e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('Administrador')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminar(@PathVariable Long id) {
        try {
            service.eliminar(id);
            return ResponseEntity.ok(ApiResponse.success("Comerciante eliminado"));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
        }
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<ApiResponse> cambiarEstado(@PathVariable Long id, @Valid @RequestBody ComercianteEstadoDTO dto) {
        try {
            Long usuarioId = (Long) request.getAttribute("usuarioId");
            ComercianteResponseDTO actualizado = service.cambiarEstado(id, dto, usuarioId);
            return ResponseEntity.ok(ApiResponse.success(actualizado));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al cambiar estado: " + e.getMessage()));
        }
    }
}
