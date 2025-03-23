package com.drivelab.autocenter.domain;

public class DomainException extends RuntimeException {
    public DomainException(Throwable cause) {
        super(cause.getMessage(), cause);
    }

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
