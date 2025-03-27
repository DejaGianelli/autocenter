package com.drivelab.autocenter.rest.purchase;

import com.drivelab.autocenter.domain.purchase.Purchase;
import com.drivelab.autocenter.domain.supplier.Supplier;
import org.springframework.stereotype.Component;

@Component
public class PurchaseCreationResponseMapping {

    public PurchaseCreationResponseBody responseBody(Purchase purchase) {
        return new PurchaseCreationResponseBody(purchase.publicId().toString(), purchase.total().decimal(),
                new PurchaseCreationResponseBody.Supplier(purchase.supplier().publicId().toString(),
                        purchase.supplier().name().toString()));
    }
}
