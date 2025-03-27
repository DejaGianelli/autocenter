package com.drivelab.autocenter.domain.purchase;

import jakarta.persistence.AttributeConverter;

public class PurchaseStatusAttributeConverter implements AttributeConverter<PurchaseStatus, String> {


    @Override
    public String convertToDatabaseColumn(PurchaseStatus purchaseStatus) {
        return purchaseStatus.key();
    }

    @Override
    public PurchaseStatus convertToEntityAttribute(String s) {
        return PurchaseStatus.fromKey(s);
    }
}
