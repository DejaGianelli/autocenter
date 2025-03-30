package com.drivelab.autocenter.domain.accountingentry;

import com.drivelab.autocenter.domain.DomainException;
import org.springframework.lang.NonNull;

import java.util.Arrays;

/**
 * <p>
 * In accounting, debit and credit are terms used to record financial transactions. Debit is the record of value
 * inflows, while credit is the record of value outflows.
 * </p>
 * <p>
 * Debit
 * Represents an increase in value in an account.
 * It increases assets or reduces liabilities.
 * Examples: cash accounts, bank accounts, inventory, operating expenses, inventory losses.
 * </p>
 * <p>
 * Credit
 * Represents a decrease in value in an account.
 * It increases liabilities or reduces assets.
 * Examples: bank accounts, when the available balance decreases.
 * </p>
 */
public enum AccountingEntryType {
    DEBIT("debit"),
    CREDIT("credit");

    public static final String DEBIT_VALUE = "debit";
    public static final String CREDIT_VALUE = "credit";

    private final String key;

    AccountingEntryType(String key) {
        this.key = key;
    }

    public static AccountingEntryType fromKey(@NonNull final String key) {
        return Arrays.stream(values())
                .filter(t -> t.key().equals(key))
                .findFirst()
                .orElseThrow(() -> new DomainException("Invalid AccountingEntryType key " + key));
    }

    public String key() {
        return key;
    }
}
