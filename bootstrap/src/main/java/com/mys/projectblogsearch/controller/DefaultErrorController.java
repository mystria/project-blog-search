package com.mys.projectblogsearch.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultErrorController implements ErrorController {

    @GetMapping("/error")
    public ResponseEntity<ErrorResponse> handleError() {

        return ResponseEntity
            .badRequest()
            .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request"));

    }

    public record ErrorResponse (Integer code, String message) {}

}
