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

    public void newMovement(@NonNull InventoryMovementCommand command) {
        Optional<Product> optional = productRepository.findByPublicId(command.publicId());
        if (optional.isEmpty()) {
            throw new ProductNotFoundException(command.publicId());
        }
        Product product = optional.get();

        Inventory inventory = inventoryRepository.findByProductForUpdate(product)
                .orElseThrow(() -> new IllegalStateException("Inventory does not exist for product " +
                        command.publicId()));

        inventory.update(command.type(), command.quantity());
        inventoryRepository.save(inventory);

        InventoryMovement movement = new InventoryMovement(command.type(), command.quantity(), inventory);
        inventoryMovementRepository.save(movement);
    }
}
