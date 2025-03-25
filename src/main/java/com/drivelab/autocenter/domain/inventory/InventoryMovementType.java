package com.drivelab.autocenter.domain.inventory;

import com.drivelab.autocenter.domain.DomainException;
import org.springframework.lang.NonNull;

import java.util.Arrays;

public enum InventoryMovementType {
    WITHDRAW("withdraw"),
    ENTRY("entry");

    private final String id;

    InventoryMovementType(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }

    public static InventoryMovementType typeFromId(@NonNull String id) {
        return Arrays.stream(values())
                .filter(t -> t.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new DomainException("Invalid inventory movement type id"));
    }
}
