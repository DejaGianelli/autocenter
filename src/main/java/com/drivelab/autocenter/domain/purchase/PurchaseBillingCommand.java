package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.Money;

import java.time.LocalDate;
import java.util.List;

public class PurchaseBillingCommand {

    private final PurchasePublicId purchaseId;
    private final List<Installment> installments;

    public PurchaseBillingCommand(PurchasePublicId purchaseId, List<Installment> installments) {
        this.purchaseId = purchaseId;
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
