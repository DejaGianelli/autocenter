package com.drivelab.autocenter.domain.accountingentry;

import com.drivelab.autocenter.domain.InternalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountingEntryRepository extends JpaRepository<AccountingEntry, InternalId> {

}
