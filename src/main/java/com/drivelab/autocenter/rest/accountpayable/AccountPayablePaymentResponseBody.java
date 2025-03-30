package com.drivelab.autocenter.rest.accountpayable;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public class AccountPayablePaymentResponseBody {

    private String publicId;

    private String status;

    private double amount;

    private LocalDate dueDate;

    private OffsetDateTime paidAt;

    private OffsetDateTime createdAt;

    public String getPublicId() {
        return publicId;
    }

    public AccountPayablePaymentResponseBody setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public AccountPayablePaymentResponseBody setStatus(String status) {
        this.status = status;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public AccountPayablePaymentResponseBody setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public AccountPayablePaymentResponseBody setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public OffsetDateTime getPaidAt() {
        return paidAt;
    }

    public AccountPayablePaymentResponseBody setPaidAt(OffsetDateTime paidAt) {
        this.paidAt = paidAt;
        return this;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public AccountPayablePaymentResponseBody setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
