package com.drivelab.autocenter.domain.product;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ProductDetailsUseCase {

    private final ProductRepository productRepository;

    public ProductDetailsUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product product(@NonNull ProductPublicId publicId) {
        Optional<Product> optional = productRepository.findByPublicId(publicId);
        if (optional.isEmpty()) {
            throw new ProductNotFoundException(publicId);
        }
        return optional.get();
    }
}
