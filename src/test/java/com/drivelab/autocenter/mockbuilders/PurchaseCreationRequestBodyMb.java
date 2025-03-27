package com.drivelab.autocenter.mockbuilders;

import com.drivelab.autocenter.rest.purchase.PurchaseCreationRequestBody;

public class PurchaseCreationRequestBodyMb {

    private String supplierId;

    public PurchaseCreationRequestBodyMb() {
    }

    public PurchaseCreationRequestBody defaultRequest() {
        setSupplierId("01jqahvbw58f7n34fpepj96zms");
        return new PurchaseCreationRequestBody(supplierId);
    }

    public String getSupplierId() {
        return supplierId;
    }

    public PurchaseCreationRequestBodyMb setSupplierId(String supplierId) {
        this.supplierId = supplierId;
        return this;
    }
}
