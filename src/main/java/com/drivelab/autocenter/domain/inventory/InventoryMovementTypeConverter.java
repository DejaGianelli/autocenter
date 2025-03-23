package com.drivelab.autocenter.domain.inventory;

import jakarta.persistence.AttributeConverter;

public class InventoryMovementTypeConverter implements AttributeConverter<InventoryMovementType, String> {
    @Override
    public String convertToDatabaseColumn(InventoryMovementType inventoryMovementType) {
        return inventoryMovementType.id();
    }

    @Override
    public InventoryMovementType convertToEntityAttribute(String string) {
        return InventoryMovementType.typeFromId(string);
    }
}
