package com.drivelab.autocenter.rest.purchase;

import com.drivelab.autocenter.domain.EntityNotFoundException;
import com.drivelab.autocenter.domain.purchase.Purchase;
import com.drivelab.autocenter.domain.purchase.PurchaseCreationCommand;
import com.drivelab.autocenter.domain.purchase.PurchaseCreationUseCase;
import com.drivelab.autocenter.domain.supplier.SupplierNotFoundException;
import com.drivelab.autocenter.rest.ProblemDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/v1/purchases")
public class PurchaseCreationRestController {

    private final PurchaseCreationUseCase useCase;
    private final PurchaseCreationRequestBodyMapping requestMapping;
    private final PurchaseCreationResponseMapping responseMapping;

    @Autowired
    public PurchaseCreationRestController(PurchaseCreationUseCase useCase,
                                          PurchaseCreationRequestBodyMapping requestMapping,
                                          PurchaseCreationResponseMapping responseMapping) {
        this.useCase = useCase;
        this.requestMapping = requestMapping;
        this.responseMapping = responseMapping;
    }

    @PostMapping
    public ResponseEntity<PurchaseCreationResponseBody> createdResponse(@RequestBody PurchaseCreationRequestBody requestBody,
                                                                        UriComponentsBuilder uriComponentsBuilder) {
        PurchaseCreationCommand command = requestMapping.command(requestBody);
        Purchase purchase = useCase.newPurchase(command);
        PurchaseCreationResponseBody responseBody = responseMapping.responseBody(purchase);
        URI uri = uriComponentsBuilder.path("/v1/purchases/{id}").buildAndExpand(purchase.publicId().toString()).toUri();
        return ResponseEntity.created(uri).body(responseBody);
    }

    @ExceptionHandler({SupplierNotFoundException.class})
    public ResponseEntity<ProblemDetails> notFoundResponse(EntityNotFoundException ex) {
        ProblemDetails problemDetails = new ProblemDetails(ex.getMessage(), BAD_REQUEST);
        return ResponseEntity.status(BAD_REQUEST).body(problemDetails);
    }
}
