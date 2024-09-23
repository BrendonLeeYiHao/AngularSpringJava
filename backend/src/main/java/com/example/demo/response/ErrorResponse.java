package com.example.demo.response;

public class ErrorResponse<T> {

    private T error;
    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(T error, String message) {
        this.error = error;
        this.message = message;
    }

    public T getError() {
        return error;
    }

    public void setError(T error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}