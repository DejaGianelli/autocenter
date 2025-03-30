package com.drivelab.autocenter.domain.accountingentry;

import com.drivelab.autocenter.domain.Money;
import com.drivelab.autocenter.domain.MoneyAttributeConverter;
import com.drivelab.autocenter.domain.financialaccount.FinancialAccountCategory;
import jakarta.persistence.Convert;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.springframework.lang.NonNull;

@Entity
@DiscriminatorValue(AccountingEntryType.CREDIT_VALUE)
public class AccountingCreditEntry extends AccountingEntry {

    @Convert(converter = MoneyAttributeConverter.class)
    private Money credit;

    protected AccountingCreditEntry() {
        super(AccountingEntryType.CREDIT);
        this.credit = Money.ZERO;
    }

    public AccountingCreditEntry(@NonNull Money credit, @NonNull FinancialAccountCategory category) {
        this();
        this.credit = credit;
        this.category = category;
    }

    public Money credit() {
        return credit;
    }
}
