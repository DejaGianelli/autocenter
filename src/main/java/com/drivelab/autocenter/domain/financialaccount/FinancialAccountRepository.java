package com.drivelab.autocenter.domain.financialaccount;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FinancialAccountRepository extends JpaRepository<FinancialAccount, Long> {

    @Cacheable(value = "financialAccounts", key = "#category.value")
    Optional<FinancialAccount> findByCategory(@Param("category") FinancialAccountCategory category);

    boolean existsByCategory(FinancialAccountCategory category);
}
