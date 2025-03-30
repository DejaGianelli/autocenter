package com.drivelab.autocenter.domain.inventory;

import com.drivelab.autocenter.domain.product.Product;
import com.drivelab.autocenter.domain.product.ProductNotFoundException;
import com.drivelab.autocenter.domain.product.ProductRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class InventoryMovementUseCase {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final InventoryMovementRepository inventoryMovementRepository;

    public InventoryMovementUseCase(InventoryRepository inventoryRepository,
                                    ProductRepository productRepository,
                                    InventoryMovementRepository inventoryMovementRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.inventoryMovementRepository = inventoryMovementRepository;
    }

    public void movement(@NonNull InventoryMovementCommand command) {
        Optional<Product> optional = productRepository.findByPublicId(command.publicId());
        if (optional.isEmpty()) {
            throw new ProductNotFoundException(command.publicId());
        }
        Product product = optional.get();
        movement(product, command.type(), command.quantity());
    }

    public void movement(@NonNull Product product,
                         @NonNull InventoryMovementType type,
                         @NonNull InventoryQuantity quantity) {
        Inventory inventory = inventoryRepository.findByProductForUpdate(product)
                .orElseThrow(() -> new InventoryForProductNotFoundException(product.publicId()));

        inventory.update(type, quantity);
        inventoryRepository.save(inventory);

        InventoryMovement movement = new InventoryMovement(type, quantity, inventory);
        inventoryMovementRepository.save(movement);
    }
}
