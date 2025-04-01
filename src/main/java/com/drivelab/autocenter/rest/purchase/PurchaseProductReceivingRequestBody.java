package com.drivelab.autocenter.rest.purchase;

public class PurchaseProductReceivingRequestBody {

    private final Integer quantity;

    public PurchaseProductReceivingRequestBody(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
