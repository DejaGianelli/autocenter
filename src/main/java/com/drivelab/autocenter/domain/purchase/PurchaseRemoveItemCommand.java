package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.product.ProductPublicId;

public class PurchaseRemoveItemCommand {

    private final PurchasePublicId purchaseId;
    private final ProductPublicId productId;

    public PurchaseRemoveItemCommand(PurchasePublicId purchaseId,
                                     ProductPublicId productId) {
        this.purchaseId = purchaseId;
        this.productId = productId;
    }

    public PurchasePublicId purchaseId() {
        return purchaseId;
    }

    public ProductPublicId productId() {
        return productId;
    }
}
