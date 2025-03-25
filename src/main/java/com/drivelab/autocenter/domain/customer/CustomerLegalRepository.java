package com.drivelab.autocenter.domain.customer;

import com.drivelab.autocenter.domain.Cnpj;
import com.drivelab.autocenter.domain.InternalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerLegalRepository extends JpaRepository<CustomerLegal, InternalId> {

    Optional<CustomerLegal> findByCnpj(Cnpj cnpj);
}
