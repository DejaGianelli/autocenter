package com.drivelab.autocenter.domain.accountpayable;

import com.drivelab.autocenter.domain.DomainEntity;
import com.drivelab.autocenter.domain.Money;
import com.drivelab.autocenter.domain.MoneyAttributeConverter;
import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@AttributeOverrides({
        @AttributeOverride(name = "origin.id", column = @Column(name = "origin_id")),
        @AttributeOverride(name = "origin.type", column = @Column(name = "origin_type"))
})
@Entity
@Table(name = "accounts_payable")
public class AccountPayable extends DomainEntity {

    @Embedded
    private AccountPayablePublicId publicId;

    @Convert(converter = AccountPayableStatusAttributeConverter.class)
    private AccountsPayableStatus status;

    @Convert(converter = MoneyAttributeConverter.class)
    private Money amount;

    private LocalDate dueDate;

    @Column(name = "paid_at_utc")
    private OffsetDateTime paidAt;

    @Embedded
    private PayableOrigin origin;

    @Column(name = "created_at_utc")
    private OffsetDateTime createdAt;

    protected AccountPayable() {
    }

    private AccountPayable(Builder builder) {
        publicId = builder.publicId;
        status = builder.status;
        createdAt = builder.createdAt;
        amount = builder.amount;
        dueDate = builder.dueDate;
        origin = builder.origin;
    }

    public void pay() {
        this.status = AccountsPayableStatus.PAID;
        this.paidAt = Instant.now().atOffset(ZoneOffset.UTC);
    }

    public AccountPayablePublicId publicId() {
        return publicId;
    }

    public AccountsPayableStatus status() {
        return status;
    }

    public Money amount() {
        return amount;
    }

    public LocalDate dueDate() {
        return dueDate;
    }

    public OffsetDateTime paidAt() {
        return paidAt;
    }

    public PayableOrigin origin() {
        return origin;
    }

    public OffsetDateTime createdAt() {
        return createdAt;
    }

    public static final class Builder {
        private final AccountPayablePublicId publicId;
        private final OffsetDateTime createdAt;
        private AccountsPayableStatus status;
        private Money amount;
        private LocalDate dueDate;
        private PayableOrigin origin;

        private Builder() {
            this.publicId = new AccountPayablePublicId();
            this.status = AccountsPayableStatus.PENDING;
            this.createdAt = Instant.now().atOffset(ZoneOffset.UTC);
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder status(AccountsPayableStatus val) {
            status = val;
            return this;
        }

        public Builder amount(Money val) {
            amount = val;
            return this;
        }

        public Builder dueDate(LocalDate val) {
            dueDate = val;
            return this;
        }

        public Builder origin(PayableOrigin val) {
            origin = val;
            return this;
        }

        public AccountPayable build() {
            return new AccountPayable(this);
        }
    }
}
