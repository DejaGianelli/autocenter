package com.drivelab.autocenter.domain.inventory;

import jakarta.persistence.Column;
import org.springframework.lang.NonNull;

public class InventoryQuantity {

    static InventoryQuantity ZERO = new InventoryQuantity(0);

    @Column(name = "quantity")
    private Integer value;

    protected InventoryQuantity() {
        //Public empty constructor needed by ORM
    }

    public InventoryQuantity(@NonNull Integer value) {
        this.value = value;
    }

    public InventoryQuantity incrementedBy(@NonNull InventoryQuantity quantity) {
        return new InventoryQuantity(this.value + quantity.value());
    }

    public InventoryQuantity decrementedBy(InventoryQuantity quantity) {
        int value = this.value - quantity.value();
        if (this.value - quantity.value() < 0) {
            throw new IllegalStateException("Inventory quantity cannot be negative");
        }
        return new InventoryQuantity(value);
    }

    public Integer value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value());
    }
}
