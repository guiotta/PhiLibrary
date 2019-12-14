package com.otta.library.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
    }

    @ExceptionHandler(value  = { Exception.class })
    protected ResponseEntity<String> handleUnknownException(Exception ex, WebRequest request) {
        String errorMessage = "Could not proccess request: %s";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(String.format(errorMessage,
                ex.getMessage()));
    }
}
