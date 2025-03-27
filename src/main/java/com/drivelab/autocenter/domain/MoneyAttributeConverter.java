package com.drivelab.autocenter.domain;

import jakarta.persistence.AttributeConverter;

public class MoneyAttributeConverter implements AttributeConverter<Money, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Money money) {
        return money.cents();
    }

    @Override
    public Money convertToEntityAttribute(Integer i) {
        return Money.create(i);
    }
}
