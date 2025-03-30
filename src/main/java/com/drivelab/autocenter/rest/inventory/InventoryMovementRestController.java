package com.drivelab.autocenter.rest.inventory;

import com.drivelab.autocenter.domain.DomainException;
import com.drivelab.autocenter.domain.EntityNotFoundException;
import com.drivelab.autocenter.domain.inventory.InventoryMovementCommand;
import com.drivelab.autocenter.domain.inventory.InventoryMovementUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/inventory")
public class InventoryMovementRestController {

    private final InventoryMovementUseCase useCase;
    private final InventoryMovementRequestMapping requestMapping;

    public InventoryMovementRestController(InventoryMovementUseCase useCase,
                                           InventoryMovementRequestMapping requestMapping) {
        this.useCase = useCase;
        this.requestMapping = requestMapping;
    }

    @PostMapping
    public ResponseEntity<Void> movementResponse(@RequestBody InventoryMovementRequestBody requestBody) {
        try {
            InventoryMovementCommand command = requestMapping.command(requestBody);
            useCase.movement(command);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException ex) {
            throw new DomainException(ex);
        }
    }
}
