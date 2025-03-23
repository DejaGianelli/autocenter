package com.drivelab.autocenter.domain.product;

import com.drivelab.autocenter.domain.inventory.Inventory;
import com.drivelab.autocenter.domain.inventory.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ProductCreationUseCase {

    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    public ProductCreationUseCase(ProductRepository productRepository,
                                  InventoryRepository inventoryRepository) {
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public Product newProduct(ProductCreationCommand command) {
        Optional<Product> optional = productRepository.findBySku(command.sku());
        if (optional.isPresent()) {
            throw new ExistentProductException(command.sku());
        }
        Product product = new Product(command.sku(), command.name());
        product = productRepository.saveAndFlush(product);

        Inventory inventory = new Inventory(product);
        inventoryRepository.save(inventory);

        return product;
    }
}
