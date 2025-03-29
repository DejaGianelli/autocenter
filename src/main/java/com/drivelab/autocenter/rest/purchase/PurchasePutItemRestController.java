package com.drivelab.autocenter.rest.purchase;

import com.drivelab.autocenter.domain.EntityNotFoundException;
import com.drivelab.autocenter.domain.product.ProductNotFoundException;
import com.drivelab.autocenter.domain.purchase.Purchase;
import com.drivelab.autocenter.domain.purchase.PurchasePutItemCommand;
import com.drivelab.autocenter.domain.purchase.PurchasePutItemUseCase;
import com.drivelab.autocenter.domain.purchase.PurchasePublicId;
import com.drivelab.autocenter.rest.ProblemDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/v1/purchases")
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

    @PostMapping("/{id}/products")
    public ResponseEntity<PurchasePutItemResponseBody> response(@PathVariable String id,
                                                                @RequestBody PurchasePutItemRequestBody requestBody) {
        PurchasePutItemCommand command = requestMapping.command(new PurchasePublicId(id), requestBody);
        Purchase purchase = useCase.udpatedPurchase(command);
        PurchasePutItemResponseBody responseBody = responseMapping.responseBody(purchase);
        return ResponseEntity.ok(responseBody);
    }

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<ProblemDetails> notFoundResponse(EntityNotFoundException ex) {
        ProblemDetails problemDetails = new ProblemDetails(ex.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(problemDetails);
    }
}
