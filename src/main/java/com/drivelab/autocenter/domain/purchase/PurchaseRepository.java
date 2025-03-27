package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.InternalId;
import com.drivelab.autocenter.domain.product.ProductPublicId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, InternalId> {

    Optional<Purchase> findByPublicId(PurchasePublicId publicId);
}
