package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.inventory.InventoryQuantity;
import com.drivelab.autocenter.domain.product.ProductPublicId;
import org.springframework.lang.NonNull;

public class PurchaseProductReceivingCommand {

    private final PurchasePublicId purchaseId;
    private final ProductPublicId productId;
    private final InventoryQuantity quantity;

    public PurchaseProductReceivingCommand(@NonNull PurchasePublicId id,
                                           @NonNull ProductPublicId productId,
                                           @NonNull InventoryQuantity quantity) {
        this.purchaseId = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public InventoryQuantity quantity() {
        return quantity;
    }

    public PurchasePublicId purchaseId() {
        return purchaseId;
    }

    public ProductPublicId productId() {
        return productId;
    }
}
