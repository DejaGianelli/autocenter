package com.drivelab.autocenter.domain.serviceorder;

import jakarta.persistence.AttributeConverter;
import org.springframework.lang.Nullable;

public class ServiceOrderObservationsAttributeConverter implements AttributeConverter<ServiceOrderObservations, String> {

    @Override
    public String convertToDatabaseColumn(@Nullable ServiceOrderObservations attribute) {
        if (attribute == null) return null;
        return attribute.getValue();
    }

    @Override
    public ServiceOrderObservations convertToEntityAttribute(@Nullable String dbData) {
        if (dbData == null) return null;
        return new ServiceOrderObservations(dbData);
    }
}
