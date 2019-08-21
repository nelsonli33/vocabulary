package com.lee.vocabulary.service.exceptions;

public class UnknownIdentifierException extends RuntimeException {
    public UnknownIdentifierException(String message) {
        super(message);
    }

    public UnknownIdentifierException(Throwable cause) {
        super(cause);
    }

    public UnknownIdentifierException(String message, Throwable cause) {
        super(message, cause);
    }
}
