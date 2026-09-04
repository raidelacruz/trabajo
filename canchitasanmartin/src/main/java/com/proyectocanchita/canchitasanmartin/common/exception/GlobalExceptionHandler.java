package com.proyectocanchita.canchitasanmartin.common.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ErrorResponseDTO> manejarRecursoNoEncontrado(RecursoNoEncontradoException exception, HttpServletRequest request){
        ErrorResponseDTO error = ErrorResponseDTO.builder()
        .timestamp(LocalDateTime.now())
        .status(HttpStatus.NOT_FOUND.value())
        .error(HttpStatus.NOT_FOUND.getReasonPhrase())
        .message(exception.getMessage())
        .path(request.getRequestURI())
        .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(RecursoConflictoException.class)
    public ResponseEntity<ErrorResponseDTO> manejarConflictoEncontrado(RecursoConflictoException exception, HttpServletRequest request){
        ErrorResponseDTO error = ErrorResponseDTO.builder()
        .timestamp(LocalDateTime.now())
        .status(HttpStatus.CONFLICT.value())
        .error(HttpStatus.CONFLICT.getReasonPhrase())
        .message(exception.getMessage())
        .path(request.getRequestURI())
        .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> manejarExcepcionDesconocida(Exception exception, HttpServletRequest request){
        ErrorResponseDTO error = ErrorResponseDTO.builder()
        .timestamp(LocalDateTime.now())
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
        .message(exception.getMessage("Ocurrió un error inesperado en el servidor. Por favor intente más tarde."))
        .path(request.getRequestURI())
        .build();
        return ResponseEntity.status(HttpStatus. INTERNAL_SERVER_ERROR).body(error);    
    }


    @ExceptionHandler(MethodArgumentValid.class)
    public ResponseEntity<ErrorResponseDTO>
}
