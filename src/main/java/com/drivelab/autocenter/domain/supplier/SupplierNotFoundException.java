package com.drivelab.autocenter.domain.supplier;

import com.drivelab.autocenter.domain.EntityNotFoundException;

public class SupplierNotFoundException extends EntityNotFoundException {
    public SupplierNotFoundException(SupplierPublicId id) {
        super("Supplier of id " + id + " does not exist");
    }
}
