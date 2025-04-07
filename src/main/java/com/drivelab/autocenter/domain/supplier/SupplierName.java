package com.drivelab.autocenter.domain.supplier;

import com.drivelab.autocenter.domain.DomainException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;

@Embeddable
public class SupplierName {

    public static final int MIN_SIZE = 1;
    public static final int MAX_SIZE = 64;

    @Column(name = "name")
    private String value;

    protected SupplierName() {
    }

    public SupplierName(@NonNull String value) {
        value = StringUtils.trim(value);
        if (StringUtils.isBlank(value)) {
            throw new DomainException("Supplier name can not be empty");
        }
        if (value.length() > 64) {
            throw new DomainException("Supplier name must be between  " + MIN_SIZE + " and " + MAX_SIZE + "characters");
        }
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
