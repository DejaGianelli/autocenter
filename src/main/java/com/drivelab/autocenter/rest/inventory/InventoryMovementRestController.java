package com.drivelab.autocenter.rest.inventory;

import com.drivelab.autocenter.domain.inventory.InventoryMovementCommand;
import com.drivelab.autocenter.domain.inventory.InventoryStandaloneMovementUseCase;
import com.drivelab.autocenter.domain.product.ProductPublicId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/inventory/{productId}")
public class InventoryMovementRestController {

    private final InventoryStandaloneMovementUseCase useCase;
    private final InventoryMovementRequestMapping requestMapping;

    public InventoryMovementRestController(InventoryStandaloneMovementUseCase useCase,
                                           InventoryMovementRequestMapping requestMapping) {
        this.useCase = useCase;
        this.requestMapping = requestMapping;
    }

    @PostMapping
    public ResponseEntity<Void> response(@PathVariable String productId,
                                         @RequestBody InventoryMovementRequestBody requestBody) {
        InventoryMovementCommand command = requestMapping.command(new ProductPublicId(productId), requestBody);
        useCase.movement(command);
        return ResponseEntity.ok().build();
    }
}
