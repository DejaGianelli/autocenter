package com.drivelab.autocenter.domain.customer;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerNaturalCreationUseCase implements CustomerCreationUseCase {

    private final CustomerNaturalRepository customerRepository;

    public CustomerNaturalCreationUseCase(CustomerNaturalRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer newCustomer(CustomerCreationCommand command) {
        Optional<CustomerNatural> optional = customerRepository.findByCpf(command.cpf());
        if (optional.isPresent()) {
            throw new ExistentCustomerNaturalException(command.cpf());
        }
        CustomerNatural customer = new CustomerNatural(command.cpf());
        return customerRepository.save(customer);
    }

    @Override
    public CustomerType type() {
        return CustomerType.NATURAL;
    }
}
