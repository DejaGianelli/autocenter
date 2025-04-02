package com.drivelab.autocenter.domain.serviceorder;

import com.drivelab.autocenter.domain.DomainException;
import org.springframework.lang.NonNull;

import java.util.Arrays;

public enum ServiceOrderStatus {
    CREATED("created");

    private final String key;

    ServiceOrderStatus(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    public static ServiceOrderStatus fromKey(@NonNull String key) {
        return Arrays.stream(values())
                .filter(t -> t.key().equals(key))
                .findFirst()
                .orElseThrow(() -> new DomainException("Invalid ServiceOrderStatus key"));
    }
}
