package com.drivelab.autocenter.rest.purchase;

import com.drivelab.autocenter.domain.Money;
import com.drivelab.autocenter.domain.product.ProductPublicId;
import com.drivelab.autocenter.domain.purchase.PurchasePutItemCommand;
import com.drivelab.autocenter.domain.purchase.PurchasePublicId;
import org.springframework.stereotype.Component;

@Component
public class PurchasePutItemRequestBodyMapping {

    public PurchasePutItemCommand command(PurchasePublicId id, PurchasePutItemRequestBody requestBody) {
        return new PurchasePutItemCommand(id, new ProductPublicId(requestBody.getProductId()),
                requestBody.getQuantity(), Money.create(requestBody.getUnitCost()));
    }
}
