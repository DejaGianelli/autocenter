package com.drivelab.autocenter.domain.customer;

import com.drivelab.autocenter.domain.EntityNotFoundException;

public class CustomerNotFoundException extends EntityNotFoundException {
    public CustomerNotFoundException(CustomerPublicId id) {
        super("Customer of id " + id + " does not exist");
    }
}
