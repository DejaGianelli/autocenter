package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.Money;
import com.drivelab.autocenter.domain.product.ProductPublicId;

public class PurchasePutItemCommand {

    private final PurchasePublicId purchaseId;
    private final ProductPublicId productId;
    private final Integer quantity;
    private final Money unitCost;

    public PurchasePutItemCommand(PurchasePublicId purchaseId,
                                  ProductPublicId productId,
                                  Integer quantity,
                                  Money unitCost) {
        this.purchaseId = purchaseId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitCost = unitCost;
    }

    public Money unitCost() {
        return unitCost;
    }

    public PurchasePublicId purchaseId() {
        return purchaseId;
    }

    public ProductPublicId productId() {
        return productId;
    }

    public Integer quantity() {
        return quantity;
    }
}
