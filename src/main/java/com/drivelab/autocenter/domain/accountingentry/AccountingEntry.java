package com.drivelab.autocenter.domain.accountingentry;

import com.drivelab.autocenter.domain.DomainEntity;
import com.drivelab.autocenter.domain.financialaccount.FinancialAccountCategory;
import com.drivelab.autocenter.domain.financialaccount.FinancialAccountCategoryAttributeConverter;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Entity
@Table(name = "accounting_entries")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class AccountingEntry extends DomainEntity {

    @Column(name = "account_category", updatable = false)
    @Convert(converter = FinancialAccountCategoryAttributeConverter.class)
    protected FinancialAccountCategory category;

    @Convert(converter = AccountingEntryTypeAttributeConverter.class)
    @Column(insertable = false, updatable = false)
    protected AccountingEntryType type;

    protected OffsetDateTime createdAt;

    protected AccountingEntry() {
        this.createdAt = Instant.now().atOffset(ZoneOffset.UTC);
    }

    public AccountingEntry(@NonNull AccountingEntryType type) {
        this();
        this.type = type;
    }

    public FinancialAccountCategory category() {
        return category;
    }

    public AccountingEntryType type() {
        return type;
    }

    public OffsetDateTime createdAt() {
        return createdAt;
    }
}
