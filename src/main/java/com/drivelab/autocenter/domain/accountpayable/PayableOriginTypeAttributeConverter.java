package com.drivelab.autocenter.domain.accountpayable;

import jakarta.persistence.AttributeConverter;

public class PayableOriginTypeAttributeConverter implements AttributeConverter<PayableOriginType, String> {

    @Override
    public String convertToDatabaseColumn(PayableOriginType attribute) {
        return attribute.key();
    }

    @Override
    public PayableOriginType convertToEntityAttribute(String dbData) {
        return PayableOriginType.fromKey(dbData);
    }
}
