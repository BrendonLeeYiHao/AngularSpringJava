package com.example.demo.exception;

public class AgeNotEighteenException extends RuntimeException {
    public AgeNotEighteenException(String message) {
        super(message);
    }
}

