package com.drivelab.autocenter.domain.customer;

import com.drivelab.autocenter.domain.Cnpj;
import com.drivelab.autocenter.domain.DomainException;

public class ExistentCustomerLegalException extends DomainException {
    public ExistentCustomerLegalException(Cnpj cnpj) {
        super("Legal customer of document " + cnpj + " already exists");
    }
}
