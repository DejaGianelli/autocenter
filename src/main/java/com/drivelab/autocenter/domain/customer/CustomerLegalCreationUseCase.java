package com.drivelab.autocenter.domain.customer;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerLegalCreationUseCase implements CustomerCreationUseCase {

    private final CustomerLegalRepository customerRepository;

    public CustomerLegalCreationUseCase(CustomerLegalRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer newCustomer(CustomerCreationCommand command) {
        Optional<CustomerLegal> optional = customerRepository.findByCnpj(command.cnpj());
        if (optional.isPresent()) {
            throw new ExistentCustomerLegalException(command.cnpj());
        }
        CustomerLegal customer = new CustomerLegal(command.cnpj());
        return customerRepository.save(customer);
    }

    @Override
    public CustomerType type() {
        return CustomerType.LEGAL;
    }
}
