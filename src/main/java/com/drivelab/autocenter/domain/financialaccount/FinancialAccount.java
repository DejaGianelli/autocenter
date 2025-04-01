package com.drivelab.autocenter.domain.financialaccount;

import com.drivelab.autocenter.domain.DomainEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.springframework.lang.NonNull;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "financial_accounts")
public class FinancialAccount extends DomainEntity {

    @NaturalId
    @Convert(converter = FinancialAccountCategoryAttributeConverter.class)
    @Column(name = "category", updatable = false)
    private FinancialAccountCategory category;

    private String name;

    protected FinancialAccount() {
    }

    public FinancialAccount(@NonNull FinancialAccountCategory category, @NonNull String name) {
        this.category = category;
        this.name = name;
    }

    public FinancialAccountCategory category() {
        return category;
    }

    public String name() {
        return name;
    }
}
