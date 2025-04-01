package com.drivelab.autocenter.domain.accountpayable;

import com.drivelab.autocenter.domain.PaymentMethod;
import jakarta.persistence.AttributeConverter;
import org.springframework.lang.Nullable;

import static java.util.Objects.isNull;

public class PaymentMethodAttributeConverter implements AttributeConverter<PaymentMethod, String> {

    @Override
    public @Nullable String convertToDatabaseColumn(@Nullable PaymentMethod attribute) {
        if (isNull(attribute)) {
            return null;
        }
        return attribute.key();
    }

    @Override
    public @Nullable PaymentMethod convertToEntityAttribute(@Nullable String dbData) {
        if (isNull(dbData)) {
            return null;
        }
        return PaymentMethod.fromKey(dbData);
    }
}
