package com.drivelab.autocenter.rest.purchase;

import com.drivelab.autocenter.domain.EntityNotFoundException;
import com.drivelab.autocenter.domain.product.ProductNotFoundException;
import com.drivelab.autocenter.domain.product.ProductPublicId;
import com.drivelab.autocenter.domain.purchase.PurchaseProductReceivingUseCase;
import com.drivelab.autocenter.domain.purchase.PurchaseProductReceivingCommand;
import com.drivelab.autocenter.domain.purchase.PurchasePublicId;
import com.drivelab.autocenter.rest.ProblemDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/v1/purchases/{purchaseId}/products-receiving/{productId}")
public class PurchaseProductReceivingRestController {

    private final PurchaseProductReceivingUseCase useCase;
    private final PurchaseProductReceivingRequestBodyMapping requestMapping;

    public PurchaseProductReceivingRestController(PurchaseProductReceivingUseCase useCase,
                                                  PurchaseProductReceivingRequestBodyMapping requestMapping) {
        this.useCase = useCase;
        this.requestMapping = requestMapping;
    }

    @PostMapping
    public ResponseEntity<Void> response(@PathVariable String purchaseId,
                                         @PathVariable String productId,
                                         @RequestBody PurchaseProductReceivingRequestBody requestBody) {
        PurchaseProductReceivingCommand command = requestMapping.command(new PurchasePublicId(purchaseId),
                new ProductPublicId(productId), requestBody);
        useCase.receiveProduct(command);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<ProblemDetails> notFoundResponse(EntityNotFoundException ex) {
        ProblemDetails problemDetails = new ProblemDetails(ex.getMessage(), BAD_REQUEST);
        return ResponseEntity.status(BAD_REQUEST).body(problemDetails);
    }
}
