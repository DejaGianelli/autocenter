package com.drivelab.autocenter.domain.serviceorder;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {

    Optional<ServiceOrder> findByPublicId(ServiceOrderPublicId id);

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            SELECT so FROM ServiceOrder so WHERE so.publicId = :publicId
            """)
    Optional<ServiceOrder> findByPublicIdLocked(ServiceOrderPublicId publicId);
}
