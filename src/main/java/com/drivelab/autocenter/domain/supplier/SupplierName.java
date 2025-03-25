package com.drivelab.autocenter.domain.supplier;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.springframework.lang.NonNull;

@Embeddable
public class SupplierName {

    @Column(name = "name")
    private String value;

    protected SupplierName() {
    }

    public SupplierName(@NonNull String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
