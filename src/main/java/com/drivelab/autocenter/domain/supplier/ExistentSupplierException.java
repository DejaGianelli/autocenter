package com.drivelab.autocenter.domain.supplier;

import com.drivelab.autocenter.domain.Cnpj;
import com.drivelab.autocenter.domain.DomainException;

public class ExistentSupplierException extends DomainException {
    public ExistentSupplierException(Cnpj cnpj) {
        super("Supplier of " + cnpj + " already exists");
    }
}
