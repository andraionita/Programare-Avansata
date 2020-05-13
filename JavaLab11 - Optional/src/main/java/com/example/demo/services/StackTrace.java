package com.example.demo.services;
/**
 * @author : Ionita Andra Paula, 2A7
 * Laborator 11 Optional
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StackTrace extends RuntimeException {
    public StackTrace() {
        super();
    }
    public StackTrace(String message, Throwable cause) {
        super(message, cause);
    }
    public StackTrace(String message) {
        super(message);
    }
    public StackTrace(Throwable cause) {
        super(cause);
    }
}
