package com.drivelab.autocenter.domain.serviceorder;

import com.drivelab.autocenter.domain.DomainException;
import org.springframework.lang.NonNull;

public class ServiceOrderObservations {

    public static final int MAX_SIZE = 256;

    private final String value;

    public ServiceOrderObservations(@NonNull String value) {
        if (value.length() > MAX_SIZE) {
            throw new DomainException("Service order observations must not exceed " + MAX_SIZE + " characters");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
