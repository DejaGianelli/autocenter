package com.drivelab.autocenter.rest.accountpayable;

import com.drivelab.autocenter.domain.accountpayable.AccountPayable;
import org.springframework.stereotype.Component;

@Component
public class AccountPayablePaymentResponseMapping {

    public AccountPayablePaymentResponseBody responseBody(AccountPayable payable) {
        return new AccountPayablePaymentResponseBody()
                .setAmount(payable.amount().decimal())
                .setCreatedAt(payable.createdAt())
                .setDueDate(payable.dueDate())
                .setPublicId(payable.publicId().toString())
                .setPaidAt(payable.paidAt())
                .setStatus(payable.status().key());
    }
}
