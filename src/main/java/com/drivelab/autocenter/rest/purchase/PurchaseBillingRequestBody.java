package com.drivelab.autocenter.rest.purchase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PurchaseBillingRequestBody {

    private List<Installment> installments;

    public PurchaseBillingRequestBody() {
        this.installments = new ArrayList<>();
    }

    public List<Installment> getInstallments() {
        return installments;
    }

    public void setInstallments(List<Installment> installments) {
        this.installments = installments;
    }

    public static class Installment {
        private Double amount;
        private LocalDate dueDate;

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public LocalDate getDueDate() {
            return dueDate;
        }

        public void setDueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
        }
    }
}
