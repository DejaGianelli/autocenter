package com.drivelab.autocenter.domain.financialaccount;

import jakarta.persistence.AttributeConverter;

public class FinancialAccountCategoryAttributeConverter implements AttributeConverter<FinancialAccountCategory, String> {

    @Override
    public String convertToDatabaseColumn(FinancialAccountCategory attribute) {
        return attribute.toString();
    }

    @Override
    public FinancialAccountCategory convertToEntityAttribute(String dbData) {
        return new FinancialAccountCategory(dbData);
    }
}
