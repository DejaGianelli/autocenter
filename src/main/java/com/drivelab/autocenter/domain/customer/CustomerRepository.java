package com.drivelab.autocenter.domain.customer;

import com.drivelab.autocenter.domain.InternalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, InternalId> {

    Optional<Customer> findByPublicId(CustomerPublicId id);

}
