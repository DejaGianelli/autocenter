package com.drivelab.autocenter.domain.product;

import com.drivelab.autocenter.domain.InternalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, InternalId> {
    Optional<Product> findBySku(Sku sku);

    Optional<Product> findByPublicId(ProductPublicId publicId);
}
