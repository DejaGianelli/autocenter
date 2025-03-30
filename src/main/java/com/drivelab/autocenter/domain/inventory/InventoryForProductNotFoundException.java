package com.drivelab.autocenter.domain.inventory;

import com.drivelab.autocenter.domain.EntityNotFoundException;
import com.drivelab.autocenter.domain.product.ProductPublicId;

public class InventoryForProductNotFoundException extends EntityNotFoundException {
    public InventoryForProductNotFoundException(ProductPublicId id) {
        super("Inventory for product of id " + id + " does not exist");
    }
}
