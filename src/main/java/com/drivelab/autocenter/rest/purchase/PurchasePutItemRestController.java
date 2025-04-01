package com.drivelab.autocenter.rest.purchase;

import com.drivelab.autocenter.domain.product.ProductPublicId;
import com.drivelab.autocenter.domain.purchase.Purchase;
import com.drivelab.autocenter.domain.purchase.PurchasePublicId;
import com.drivelab.autocenter.domain.purchase.PurchasePutItemCommand;
import com.drivelab.autocenter.domain.purchase.PurchasePutItemUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/purchases/{purchaseId}/products/{productId}")
public class PurchasePutItemRestController {

    private final PurchasePutItemUseCase useCase;
    private final PurchasePutItemRequestBodyMapping requestMapping;
    private final PurchasePutItemResponseMapping responseMapping;

    @Autowired
    public PurchasePutItemRestController(PurchasePutItemUseCase useCase,
                                         PurchasePutItemRequestBodyMapping requestMapping,
                                         PurchasePutItemResponseMapping responseMapping) {
        this.useCase = useCase;
        this.requestMapping = requestMapping;
        this.responseMapping = responseMapping;
    }

    @PutMapping
    public ResponseEntity<PurchasePutItemResponseBody> response(@PathVariable String purchaseId,
                                                                @PathVariable String productId,
                                                                @RequestBody PurchasePutItemRequestBody requestBody) {
        PurchasePutItemCommand command = requestMapping.command(new PurchasePublicId(purchaseId),
                new ProductPublicId(productId), requestBody);
        Purchase purchase = useCase.udpatedPurchase(command);
        PurchasePutItemResponseBody responseBody = responseMapping.responseBody(purchase);
        return ResponseEntity.ok(responseBody);
    }
}
