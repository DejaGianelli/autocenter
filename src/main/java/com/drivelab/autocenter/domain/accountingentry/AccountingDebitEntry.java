package com.drivelab.autocenter.domain.accountingentry;

import com.drivelab.autocenter.domain.Money;
import com.drivelab.autocenter.domain.MoneyAttributeConverter;
import com.drivelab.autocenter.domain.financialaccount.FinancialAccountCategory;
import jakarta.persistence.Convert;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.springframework.lang.NonNull;

@Entity
@DiscriminatorValue(AccountingEntryType.DEBIT_VALUE)
public class AccountingDebitEntry extends AccountingEntry {

    @Convert(converter = MoneyAttributeConverter.class)
    private Money debit;

    protected AccountingDebitEntry() {
        super(AccountingEntryType.DEBIT);
        this.debit = Money.ZERO;
    }

    public AccountingDebitEntry(@NonNull Money debit, @NonNull FinancialAccountCategory category) {
        this();
        this.debit = debit;
        this.category = category;
    }

    public Money debit() {
        return debit;
    }
}
