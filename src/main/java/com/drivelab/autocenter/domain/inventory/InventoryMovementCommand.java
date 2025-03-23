package com.drivelab.autocenter.domain.inventory;

import com.drivelab.autocenter.domain.product.ProductPublicId;

public class InventoryMovementCommand {
    private final ProductPublicId publicId;
    private final InventoryQuantity quantity;
    private final InventoryMovementType type;

    public InventoryMovementCommand(ProductPublicId publicId, InventoryQuantity quantity, InventoryMovementType type) {
        this.publicId = publicId;
        this.quantity = quantity;
        this.type = type;
    }

    public InventoryMovementType type() {
        return type;
    }

    public ProductPublicId publicId() {
        return publicId;
    }

    public InventoryQuantity quantity() {
        return quantity;
    }
}
