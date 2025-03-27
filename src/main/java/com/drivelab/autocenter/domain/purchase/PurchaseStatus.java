package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.DomainException;
import org.springframework.lang.NonNull;

import java.util.Arrays;

public enum PurchaseStatus {

    CREATED("created"),
    BILLED("billed");

    private final String key;

    PurchaseStatus(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    public static PurchaseStatus fromKey(@NonNull String key) {
        return Arrays.stream(values())
                .filter(t -> t.key().equals(key))
                .findFirst()
                .orElseThrow(() -> new DomainException("Invalid PurchaseStatus key"));
    }
}
