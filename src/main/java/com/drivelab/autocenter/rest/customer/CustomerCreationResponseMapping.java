package com.drivelab.autocenter.rest.customer;

import com.drivelab.autocenter.domain.customer.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerCreationResponseMapping {

    public CustomerCreationResponseBody responseBody(Customer customer) {
        switch (customer.type()) {
            case LEGAL -> {
                return new CustomerCreationResponseBody(customer.publicId().toString(),
                        customer.type().toString());
            }
            case NATURAL -> {
                return new CustomerCreationResponseBody(customer.publicId().toString(),
                        customer.type().toString());
            }
            default -> throw new IllegalArgumentException("Customer type " + customer.type() + " not implemented");
        }
    }
}
