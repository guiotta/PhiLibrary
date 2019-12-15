package com.otta.movies.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Classe para gerênciar os erros da aplicação.
 * @author Guilherme
 *
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(value  = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<String> handleIllegalException(RuntimeException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(value  = { Exception.class })
    protected ResponseEntity<String> handleUnknownException(Exception ex, WebRequest request) {
        String errorMessage = "Could not proccess request: %s";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(String.format(errorMessage,
                ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
