package com.drivelab.autocenter.domain;

public class EntityNotFoundException extends DomainException {
    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
