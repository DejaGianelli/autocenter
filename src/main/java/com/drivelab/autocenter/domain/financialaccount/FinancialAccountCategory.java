package com.drivelab.autocenter.domain.financialaccount;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.springframework.lang.NonNull;

import java.util.Objects;

@Embeddable
public class FinancialAccountCategory {

    @Column(name = "category")
    private final String value;

    public FinancialAccountCategory(@NonNull String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FinancialAccountCategory that = (FinancialAccountCategory) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
