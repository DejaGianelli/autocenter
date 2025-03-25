package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.InternalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, InternalId> {
}
