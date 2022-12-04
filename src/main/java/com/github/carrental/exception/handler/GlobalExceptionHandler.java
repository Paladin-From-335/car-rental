package com.github.carrental.exception.handler;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleOtherExceptions(Exception e) {
        return composeResponse(e, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> composeResponse(Exception e, HttpStatus status) {
        return ResponseEntity.status(status)
                .body(ErrorResponse.of(status.value(), e.getMessage()));

    }

    @RequiredArgsConstructor(staticName = "of")
    @Data
    private static class ErrorResponse {
        private final Boolean success = false;
        private final Integer code;
        private final String message;
    }
}
