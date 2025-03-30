package com.drivelab.autocenter.rest.purchase;

import com.drivelab.autocenter.domain.EntityNotFoundException;
import com.drivelab.autocenter.domain.product.ProductNotFoundException;
import com.drivelab.autocenter.domain.purchase.PurchaseGoodReceivingUseCase;
import com.drivelab.autocenter.domain.purchase.PurchaseGoodsReceivingCommand;
import com.drivelab.autocenter.domain.purchase.PurchasePublicId;
import com.drivelab.autocenter.rest.ProblemDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/v1/purchases/{purchaseId}")
public class PurchaseGoodsReceivingRestController {

    private final PurchaseGoodReceivingUseCase useCase;
    private final PurchaseGoodsReceivingRequestBodyMapping requestMapping;

    public PurchaseGoodsReceivingRestController(PurchaseGoodReceivingUseCase useCase,
                                                PurchaseGoodsReceivingRequestBodyMapping requestMapping) {
        this.useCase = useCase;
        this.requestMapping = requestMapping;
    }

    @PostMapping("/goods-receiving")
    public ResponseEntity<Void> response(@PathVariable String purchaseId,
                                         @RequestBody PurchaseGoodsReceivingRequestBody requestBody) {
        PurchaseGoodsReceivingCommand command = requestMapping.command(new PurchasePublicId(purchaseId), requestBody);
        useCase.goodsReceived(command);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<ProblemDetails> notFoundResponse(EntityNotFoundException ex) {
        ProblemDetails problemDetails = new ProblemDetails(ex.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(problemDetails);
    }
}
