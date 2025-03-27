package com.drivelab.autocenter.rest.purchase;

import com.drivelab.autocenter.domain.purchase.PurchaseCreationCommand;
import com.drivelab.autocenter.domain.supplier.SupplierPublicId;
import org.springframework.stereotype.Component;

@Component
public class PurchaseCreationRequestBodyMapping {

    public PurchaseCreationCommand command(PurchaseCreationRequestBody requestBody) {
        return new PurchaseCreationCommand(new SupplierPublicId(requestBody.getSupplierId()));
    }
}
