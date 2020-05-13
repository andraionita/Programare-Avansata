package com.example.demo.services;
/**
 * @author : Ionita Andra Paula, 2A7
 * Laborator 11 Optional
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomException extends RuntimeException {
    public CustomException() {
        super();
    }
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
    public CustomException(String message) {
        super(message);
    }
    public CustomException(Throwable cause) {
        super(cause);
    }
}
