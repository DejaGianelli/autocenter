package com.drivelab.autocenter.rest.inventory;

public class InventoryMovementRequestBody {
    private Integer quantity;
    private String type;
    private Double cost;

    public Double getCost() {
        return cost;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getType() {
        return type;
    }
}
