package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.DomainException;
import com.drivelab.autocenter.domain.Money;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;

public class PurchaseBillingCommand {

    private final PurchasePublicId purchaseId;
    private final List<Installment> installments;

    public PurchaseBillingCommand(@NonNull PurchasePublicId purchaseId, @NonNull List<Installment> installments) {
        this.purchaseId = purchaseId;
        if (installments.isEmpty()) {
            throw new DomainException("Installments must be informed");
        }
        this.installments = installments;
    }

    public PurchasePublicId purchaseId() {
        return purchaseId;
    }

    public List<Installment> installments() {
        return installments;
    }

    public static class Installment {
        private final Money amount;
        private final LocalDate dueDate;

        public Installment(Money amount, LocalDate dueDate) {
            this.amount = amount;
            this.dueDate = dueDate;
        }

        public Money amount() {
            return amount;
        }

        public LocalDate dueDate() {
            return dueDate;
        }
    }
}
