package com.drivelab.autocenter.domain.accountpayable;

import com.drivelab.autocenter.domain.InternalId;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AccountPayableRepository extends JpaRepository<AccountPayable, InternalId> {

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            SELECT ap FROM AccountPayable ap WHERE ap.publicId = :publicId
            """)
    Optional<AccountPayable> findByPublicIdLocked(AccountPayablePublicId publicId);
}
