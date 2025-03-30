package com.drivelab.autocenter.domain.accountpayable;

import com.drivelab.autocenter.domain.DomainException;
import org.springframework.lang.NonNull;

import java.util.Arrays;

public enum PayableOriginType {
    PURCHASE("purchase");

    private final String key;

    PayableOriginType(String key) {
        this.key = key;
    }

    public static PayableOriginType fromKey(@NonNull final String key) {
        return Arrays.stream(values())
                .filter(t -> t.key().equals(key))
                .findFirst()
                .orElseThrow(() -> new DomainException("Invalid PayableOriginType key " + key));
    }

    public String key() {
        return key;
    }
}
