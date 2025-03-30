package com.drivelab.autocenter.rest.accountpayable;

public class AccountPayablePaymentRequestBody {
    private String paymentMethod;

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public AccountPayablePaymentRequestBody setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }
}
