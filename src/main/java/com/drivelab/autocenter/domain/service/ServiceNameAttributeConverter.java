package com.drivelab.autocenter.domain.service;

import jakarta.persistence.AttributeConverter;

public class ServiceNameAttributeConverter implements AttributeConverter<ServiceName, String> {

    @Override
    public String convertToDatabaseColumn(ServiceName attribute) {
        return attribute.getValue();
    }

    @Override
    public ServiceName convertToEntityAttribute(String dbData) {
        return new ServiceName(dbData);
    }
}
