package com.drivelab.autocenter.domain.customer;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface CustomerCreationUseCase {
    Customer newCustomer(CustomerCreationCommand command);

    CustomerType type();
}
