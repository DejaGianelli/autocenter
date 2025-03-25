package com.drivelab.autocenter.domain.customer;

import com.drivelab.autocenter.domain.Cpf;
import com.drivelab.autocenter.domain.DomainException;

public class ExistentCustomerNaturalException extends DomainException {
    public ExistentCustomerNaturalException(Cpf cpf) {
        super("Natural customer of document " + cpf + " already exists");
    }
}
