package com.drivelab.autocenter.domain.accountpayable;

import com.drivelab.autocenter.domain.PaymentMethod;

public class AccountPayablePaymentCommand {
    private final PaymentMethod paymentMethod;
    private final AccountPayablePublicId publicId;

    public AccountPayablePaymentCommand(PaymentMethod paymentMethod, AccountPayablePublicId publicId) {
        this.paymentMethod = paymentMethod;
        this.publicId = publicId;
    }

    public PaymentMethod paymentMethod() {
        return paymentMethod;
    }

    public AccountPayablePublicId publicId() {
        return publicId;
    }
}
