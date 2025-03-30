package com.drivelab.autocenter.domain.financialaccount;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.springframework.lang.NonNull;

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
}
