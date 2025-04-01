package com.drivelab.autocenter.rest.purchase;

import com.drivelab.autocenter.domain.inventory.InventoryQuantity;
import com.drivelab.autocenter.domain.product.ProductPublicId;
import com.drivelab.autocenter.domain.purchase.PurchaseProductReceivingCommand;
import com.drivelab.autocenter.domain.purchase.PurchasePublicId;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class PurchaseProductReceivingRequestBodyMapping {

    public PurchaseProductReceivingCommand command(@NonNull PurchasePublicId purchaseId,
                                                   @NonNull ProductPublicId productPublicId,
                                                   @NonNull PurchaseProductReceivingRequestBody requestBody) {
        return new PurchaseProductReceivingCommand(purchaseId, productPublicId,
                new InventoryQuantity(requestBody.getQuantity()));
    }
}
