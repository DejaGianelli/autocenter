package com.drivelab.autocenter.domain.supplier;

import com.drivelab.autocenter.domain.Cnpj;

public class ExistentSupplierException extends RuntimeException {
    public ExistentSupplierException(Cnpj cnpj) {
        super("Supplier of " + cnpj + " already exists");
    }
}
