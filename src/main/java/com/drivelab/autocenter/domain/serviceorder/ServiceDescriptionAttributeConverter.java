package com.drivelab.autocenter.domain.serviceorder;

import jakarta.persistence.AttributeConverter;
import org.springframework.lang.Nullable;

public class ServiceDescriptionAttributeConverter implements AttributeConverter<ServiceDescription, String> {

    @Override
    public String convertToDatabaseColumn(@Nullable ServiceDescription attribute) {
        if (attribute == null) return null;
        return attribute.getValue();
    }

    @Override
    public ServiceDescription convertToEntityAttribute(@Nullable String dbData) {
        if (dbData == null) return null;
        return new ServiceDescription(dbData);
    }
}
