package com.drivelab.autocenter.rest.purchase;

import com.drivelab.autocenter.domain.product.ProductPublicId;
import com.drivelab.autocenter.domain.purchase.Purchase;
import com.drivelab.autocenter.domain.purchase.PurchasePublicId;
import com.drivelab.autocenter.domain.purchase.PurchaseRemoveItemCommand;
import com.drivelab.autocenter.domain.purchase.PurchaseRemoveItemUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/purchases/{purchaseId}/products/{productId}")
public class PurchaseRemoveItemRestController {

    private final PurchaseRemoveItemUseCase useCase;
    private final PurchaseRemoveItemRequestBodyMapping requestMapping;
    private final PurchaseRemoveItemResponseMapping responseMapping;

    @Autowired
    public PurchaseRemoveItemRestController(PurchaseRemoveItemUseCase useCase,
                                            PurchaseRemoveItemRequestBodyMapping requestMapping,
                                            PurchaseRemoveItemResponseMapping responseMapping) {
        this.useCase = useCase;
        this.requestMapping = requestMapping;
        this.responseMapping = responseMapping;
    }

    @DeleteMapping
    public ResponseEntity<PurchaseRemoveItemResponseBody> response(@PathVariable String purchaseId,
                                                                   @PathVariable String productId) {
        PurchaseRemoveItemCommand command = requestMapping.command(new PurchasePublicId(purchaseId),
                new ProductPublicId(productId));
        Purchase purchase = useCase.udpatedPurchase(command);
        PurchaseRemoveItemResponseBody responseBody = responseMapping.responseBody(purchase);
        return ResponseEntity.ok(responseBody);
    }
}
