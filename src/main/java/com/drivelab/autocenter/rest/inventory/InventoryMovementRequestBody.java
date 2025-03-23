package com.drivelab.autocenter.rest.inventory;

public class InventoryMovementRequestBody {
    private String productId;
    private Integer quantity;
    private String type;

    public String getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getType() {
        return type;
    }
}
