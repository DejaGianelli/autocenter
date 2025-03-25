package com.drivelab.autocenter.domain.customer;

import jakarta.persistence.AttributeConverter;

public class CustomerTypeAttributeConverter implements AttributeConverter<CustomerType, String> {

    @Override
    public String convertToDatabaseColumn(CustomerType customerType) {
        return customerType.key();
    }

    @Override
    public CustomerType convertToEntityAttribute(String s) {
        return CustomerType.fromKey(s);
    }
}
