package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.supplier.SupplierPublicId;

public class PurchaseCreationCommand {

    private final SupplierPublicId supplierId;

    public PurchaseCreationCommand(SupplierPublicId supplierId) {
        this.supplierId = supplierId;
    }

    public SupplierPublicId supplierId() {
        return supplierId;
    }
}
