package com.drivelab.autocenter.rest.accountpayable;

import com.drivelab.autocenter.domain.PaymentMethod;
import com.drivelab.autocenter.domain.accountpayable.AccountPayablePaymentCommand;
import com.drivelab.autocenter.domain.accountpayable.AccountPayablePublicId;
import org.springframework.stereotype.Component;

@Component
public class AccountPayablePaymentRequestMapping {

    public AccountPayablePaymentCommand command(AccountPayablePublicId id, AccountPayablePaymentRequestBody payable) {
        return new AccountPayablePaymentCommand(PaymentMethod.fromKey(payable.getPaymentMethod()), id);
    }
}
