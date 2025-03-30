package com.drivelab.autocenter.domain.financialaccount;

import com.drivelab.autocenter.domain.InternalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FinancialAccountRepository extends JpaRepository<FinancialAccount, InternalId> {

    Optional<FinancialAccount> findByCategory(FinancialAccountCategory category);

    boolean existsByCategory(FinancialAccountCategory category);
}
