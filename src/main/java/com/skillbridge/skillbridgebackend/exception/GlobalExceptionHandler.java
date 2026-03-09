package com.skillbridge.skillbridgebackend.exception;

import java.time.Instant;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //  VALIDATION error (DTO @Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        ApiError body = ApiError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation error")
                .path(request.getRequestURI())
                .errors(errors)
                .build();

        return ResponseEntity.badRequest().body(body);
    }

    //  Біздің RuntimeException-дар (CV табылмады, Internship табылмады, т.б.)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntime(
            RuntimeException ex,
            HttpServletRequest request
    ) {
        ApiError body = ApiError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad request")
                .path(request.getRequestURI())
                .errors(List.of(ex.getMessage()))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    //  Бәрін ұстайтын соңғы қорғаныс
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAny(
            Exception ex,
            HttpServletRequest request
    ) {
        ApiError body = ApiError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal server error")
                .path(request.getRequestURI())
                .errors(List.of("Серверде қате болды"))
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler(NotFoundException.class)
public ResponseEntity<ApiError> handleNotFound(
        NotFoundException ex,
        HttpServletRequest request
) {

    ApiError body = ApiError.builder()
            .timestamp(Instant.now())
            .status(HttpStatus.NOT_FOUND.value())
            .error("Not found")
            .path(request.getRequestURI())
            .errors(List.of(ex.getMessage()))
            .build();

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
}

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequest(
            BadRequestException ex,
            HttpServletRequest request
    ) {
        ApiError body = ApiError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad request")
                .path(request.getRequestURI())
                .errors(List.of(ex.getMessage()))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}