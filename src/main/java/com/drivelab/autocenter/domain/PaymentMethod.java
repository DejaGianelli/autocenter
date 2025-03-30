package com.drivelab.autocenter.domain;

import org.springframework.lang.NonNull;

import java.util.Arrays;

public enum PaymentMethod {
    CASH("cash"),
    CREDIT_CARD("credit_card"),
    DEBIT_CARD("debit_card"),
    CHECK("check"),
    PIX("pix");

    private final String key;

    PaymentMethod(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    public static PaymentMethod fromKey(@NonNull String key) {
        return Arrays.stream(values())
                .filter(t -> t.key().equals(key))
                .findFirst()
                .orElseThrow(() -> new DomainException("Invalid PaymentMethod key"));
    }
}
