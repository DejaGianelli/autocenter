package com.drivelab.autocenter.rest.purchase;

import com.drivelab.autocenter.domain.EntityNotFoundException;
import com.drivelab.autocenter.domain.financialaccount.FinancialAccountNotFoundException;
import com.drivelab.autocenter.domain.purchase.PurchaseBillingCommand;
import com.drivelab.autocenter.domain.purchase.PurchaseBillingUseCase;
import com.drivelab.autocenter.domain.purchase.PurchasePublicId;
import com.drivelab.autocenter.rest.ProblemDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/v1/purchases/{purchaseId}")
public class PurchaseBillingRestController {

    private final PurchaseBillingUseCase useCase;
    private final PurchaseBillingRequestBodyMapping requestMapping;

    public PurchaseBillingRestController(PurchaseBillingUseCase useCase,
                                         PurchaseBillingRequestBodyMapping requestMapping) {
        this.useCase = useCase;
        this.requestMapping = requestMapping;
    }

    @PostMapping("/billing")
    public ResponseEntity<Void> response(@PathVariable String purchaseId,
                                         @RequestBody PurchaseBillingRequestBody requestBody) {
        PurchaseBillingCommand command = requestMapping.command(new PurchasePublicId(purchaseId), requestBody);
        useCase.billedPurchase(command);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler({FinancialAccountNotFoundException.class})
    public ResponseEntity<ProblemDetails> notFoundResponse(EntityNotFoundException ex) {
        ProblemDetails problemDetails = new ProblemDetails(ex.getMessage(), BAD_REQUEST);
        return ResponseEntity.status(BAD_REQUEST).body(problemDetails);
    }
}
