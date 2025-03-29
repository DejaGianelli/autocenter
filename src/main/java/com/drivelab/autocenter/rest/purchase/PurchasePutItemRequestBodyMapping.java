package com.drivelab.autocenter.rest.purchase;

import com.drivelab.autocenter.domain.Money;
import com.drivelab.autocenter.domain.product.ProductPublicId;
import com.drivelab.autocenter.domain.purchase.PurchasePublicId;
import com.drivelab.autocenter.domain.purchase.PurchasePutItemCommand;
import org.springframework.stereotype.Component;

@Component
public class PurchasePutItemRequestBodyMapping {

    public PurchasePutItemCommand command(PurchasePublicId purchaseId,
                                          ProductPublicId productId,
                                          PurchasePutItemRequestBody requestBody) {
        return new PurchasePutItemCommand(purchaseId, productId, requestBody.getQuantity(),
                Money.create(requestBody.getUnitCost()));
    }
}
