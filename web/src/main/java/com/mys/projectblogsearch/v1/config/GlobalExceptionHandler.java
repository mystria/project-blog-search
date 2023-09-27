package com.mys.projectblogsearch.v1.config;

import com.mys.projectblogsearch.v1.response.StandardFailureResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardFailureResponse> handleException(MethodArgumentTypeMismatchException e) {

        String message = String.format("'%s' is not valid type for '%s'", e.getValue(), e.getName());
        return ResponseEntity
            .badRequest()
            .body(StandardFailureResponse.of(HttpStatus.BAD_REQUEST.value(), message));

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardFailureResponse> handleException(RuntimeException e) {

        return ResponseEntity
            .badRequest()
            .body(StandardFailureResponse.of(HttpStatus.BAD_REQUEST.value(), e.getMessage()));

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardFailureResponse> handleException(Exception e) {

        return ResponseEntity
            .internalServerError()
            .body(StandardFailureResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));

    }

}
