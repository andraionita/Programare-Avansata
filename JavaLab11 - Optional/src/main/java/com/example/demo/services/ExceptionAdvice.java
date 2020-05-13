package com.example.demo.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * @author : Ionita Andra Paula, 2A7
 * Laborator 11 Optional
 */

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(value = StackTrace.class)
    public ResponseEntity<String> exception(StackTrace e) {
        return new ResponseEntity<>(" Exception Handler: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }
}