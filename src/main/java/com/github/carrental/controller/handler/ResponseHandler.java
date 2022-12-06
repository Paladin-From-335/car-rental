package com.github.carrental.controller.handler;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

    public static ResponseEntity<Response> composeResponse(HttpStatus status) {
        return ResponseEntity.status(status)
                .body(Response.of(status.value(), status.getReasonPhrase()));
    }

    public static ResponseEntity<Response> composeResponse(Object body, HttpStatus status) {
        return ResponseEntity.status(status)
                .body(Response.of(status.value(), body));
    }

    @RequiredArgsConstructor(staticName = "of")
    @Data
    private static class Response {
        private final Boolean success = true;
        private final Integer code;
        private final Object message;
    }
}
