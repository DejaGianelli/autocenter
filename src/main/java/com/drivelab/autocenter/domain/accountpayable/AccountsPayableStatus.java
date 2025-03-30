package com.drivelab.autocenter.domain.accountpayable;

import com.drivelab.autocenter.domain.DomainException;
import org.springframework.lang.NonNull;

import java.util.Arrays;

public enum AccountsPayableStatus {
    PENDING("pending"),
    PAID("paid"),
    OVERDUE("overdue");

    private final String key;

    AccountsPayableStatus(String key) {
        this.key = key;
    }

    public static AccountsPayableStatus fromKey(@NonNull final String key) {
        return Arrays.stream(values())
                .filter(t -> t.key().equals(key))
                .findFirst()
                .orElseThrow(() -> new DomainException("Invalid AccountsPayableStatus key " + key));
    }

    public String key() {
        return key;
    }
}
