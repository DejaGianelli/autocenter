package com.drivelab.autocenter.domain.inventory;

import com.drivelab.autocenter.domain.Money;
import com.drivelab.autocenter.domain.product.ProductPublicId;
import org.springframework.lang.NonNull;

public class InventoryMovementCommand {
    private final ProductPublicId productId;
    private final InventoryQuantity quantity;
    private final InventoryMovementType type;
    private final Money cost;

    public InventoryMovementCommand(@NonNull ProductPublicId productId,
                                    @NonNull InventoryQuantity quantity,
                                    @NonNull InventoryMovementType type,
                                    @NonNull Money cost) {
        this.productId = productId;
        this.quantity = quantity;
        this.type = type;
        this.cost = cost;
    }

    public Money cost() {
        return cost;
    }

    public InventoryMovementType type() {
        return type;
    }

    public ProductPublicId publicId() {
        return productId;
    }

    public InventoryQuantity quantity() {
        return quantity;
    }
}
