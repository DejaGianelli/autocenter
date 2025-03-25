package com.drivelab.autocenter.domain.customer;

import com.drivelab.autocenter.domain.Cpf;
import com.drivelab.autocenter.domain.InternalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerNaturalRepository extends JpaRepository<CustomerNatural, InternalId> {

    Optional<CustomerNatural> findByCpf(Cpf cpf);

}
