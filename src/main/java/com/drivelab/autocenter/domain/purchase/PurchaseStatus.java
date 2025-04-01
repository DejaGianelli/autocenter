package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.DomainException;
import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.Set;

public enum PurchaseStatus {

    CREATED("created", Set.of("billed")),
    BILLED("billed", Set.of("received")),
    RECEIVED("received", Set.of());

    private final String key;
    private final Set<String> nextAllowedList;

    PurchaseStatus(String key, Set<String> nextAllowedList) {
        this.key = key;
        this.nextAllowedList = nextAllowedList;
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

    public boolean canChangeTo(@NonNull PurchaseStatus nextStatus) {
        return nextAllowedList.contains(nextStatus.key);
    }

    @Override
    public String toString() {
        return key;
    }
}
