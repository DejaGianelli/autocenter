package com.drivelab.autocenter.rest.purchase;

public class PurchaseCreationRequestBody {

    private String supplierId;

    public PurchaseCreationRequestBody(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public PurchaseCreationRequestBody setSupplierId(String supplierId) {
        this.supplierId = supplierId;
        return this;
    }
}
