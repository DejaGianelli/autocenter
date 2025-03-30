package com.drivelab.autocenter.domain.accountpayable;

import com.drivelab.autocenter.domain.EntityNotFoundException;

public class AccountPayableNotFoundException extends EntityNotFoundException {
    public AccountPayableNotFoundException(AccountPayablePublicId id) {
        super("Account Payable of id " + id + " does not exist");
    }
}
