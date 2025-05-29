package com.comercio.exception;

import com.comercio.util.ApiResponse;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> manejarNoEncontrado(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> manejarValidacion(MethodArgumentNotValidException ex) {
        String errores = ex.getBindingResult()
                           .getFieldErrors()
                           .stream()
                           .map(e -> e.getField() + ": " + e.getDefaultMessage())
                           .collect(Collectors.joining(", "));
        return ResponseEntity.badRequest().body(ApiResponse.error("Error de validación: " + errores));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> manejarExcepcionGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(ApiResponse.error("Error interno: " + ex.getMessage()));
    }
}
