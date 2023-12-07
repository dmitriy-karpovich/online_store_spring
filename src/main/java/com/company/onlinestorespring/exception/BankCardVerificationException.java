package com.company.onlinestorespring.exception;

public class BankCardVerificationException extends Exception {

    public BankCardVerificationException(String message) {
        super(message);
    }

    public BankCardVerificationException(String message, Exception exception) {
        super(message, exception);
    }

    public BankCardVerificationException(Exception exception) {
        super(exception);
    }
}