package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.InternalId;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, InternalId> {

    Optional<Purchase> findByPublicId(PurchasePublicId publicId);

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            SELECT p FROM Purchase p WHERE p.publicId = :publicId
            """)
    Optional<Purchase> findByPublicIdLocked(PurchasePublicId publicId);
}
