package com.drivelab.autocenter.domain.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ProductUpdateUseCase {

    private final ProductRepository productRepository;

    public ProductUpdateUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product updatedProduct(ProductUpdateCommand command) {
        Optional<Product> optional = productRepository.findByPublicId(command.publicId());
        if (optional.isEmpty()) {
            throw new ProductNotFoundException(command.publicId());
        }
        Product product = optional.get();
        product.update(command);
        productRepository.save(product);
        return product;
    }
}
