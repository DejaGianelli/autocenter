package com.drivelab.autocenter.rest.inventory;

import com.drivelab.autocenter.domain.Money;
import com.drivelab.autocenter.domain.inventory.InventoryMovementCommand;
import com.drivelab.autocenter.domain.inventory.InventoryMovementType;
import com.drivelab.autocenter.domain.inventory.InventoryQuantity;
import com.drivelab.autocenter.domain.product.ProductPublicId;
import org.springframework.stereotype.Component;

@Component
public class InventoryMovementRequestMapping {
    public InventoryMovementCommand command(ProductPublicId productId, InventoryMovementRequestBody requestBody) {
        return new InventoryMovementCommand(
                productId,
                new InventoryQuantity(requestBody.getQuantity()),
                InventoryMovementType.typeFromId(requestBody.getType()),
                Money.create(requestBody.getCost())
        );
    }
}
