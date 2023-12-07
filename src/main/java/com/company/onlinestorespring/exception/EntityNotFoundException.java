package com.company.onlinestorespring.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Exception exception) {
        super(message, exception);
    }

    public EntityNotFoundException(Exception exception) {
        super(exception);
    }
}