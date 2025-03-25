package com.drivelab.autocenter.domain.customer;

import com.drivelab.autocenter.domain.DomainException;
import org.springframework.lang.NonNull;

import java.util.Arrays;

public enum CustomerType {

    LEGAL("legal"),
    NATURAL("natural");

    public static final String LEGAL_KEY_VALUE = "legal";
    public static final String NATURAL_KEY_VALUE = "natural";

    private final String key;

    CustomerType(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    public static CustomerType fromKey(@NonNull final String key) {
        return Arrays.stream(values())
                .filter(t -> t.key().equals(key))
                .findFirst()
                .orElseThrow(() -> new DomainException("Invalid CustomerType key " + key));
    }

    @Override
    public String toString() {
        return key;
    }
}
