package com.drivelab.autocenter.rest.purchase;

public class PurchasePutItemRequestBody {

    private Integer quantity;
    private Integer unitCost;

    public Integer getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Integer unitCost) {
        this.unitCost = unitCost;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
