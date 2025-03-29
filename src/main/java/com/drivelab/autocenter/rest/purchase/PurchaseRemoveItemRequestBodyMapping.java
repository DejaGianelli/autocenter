package com.drivelab.autocenter.rest.purchase;

import com.drivelab.autocenter.domain.product.ProductPublicId;
import com.drivelab.autocenter.domain.purchase.PurchasePublicId;
import com.drivelab.autocenter.domain.purchase.PurchaseRemoveItemCommand;
import org.springframework.stereotype.Component;

@Component
public class PurchaseRemoveItemRequestBodyMapping {

    public PurchaseRemoveItemCommand command(PurchasePublicId purchaseId, ProductPublicId productId) {
        return new PurchaseRemoveItemCommand(purchaseId, productId);
    }
}
