package com.drivelab.autocenter.domain.serviceorder;

import jakarta.persistence.AttributeConverter;
import org.springframework.lang.NonNull;

public class ServiceOrderStatusAttributeConverter implements AttributeConverter<ServiceOrderStatus, String> {
    @Override
    public String convertToDatabaseColumn(@NonNull ServiceOrderStatus attribute) {
        return attribute.key();
    }

    @Override
    public ServiceOrderStatus convertToEntityAttribute(@NonNull String dbData) {
        return ServiceOrderStatus.fromKey(dbData);
    }
}
