package com.example.demo.services;

/**
 * @author : Ionita Andra Paula, 2A7
 * Laborator 11 Optional
 */
import org.springframework.stereotype.Component;


@Component
public class CustomError extends Error {
    public CustomError() {
        super();
    }

    public CustomError(String message) {
        super(message);
    }
}
