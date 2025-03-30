package com.drivelab.autocenter.domain.accountingentry;

import jakarta.persistence.AttributeConverter;

public class AccountingEntryTypeAttributeConverter implements AttributeConverter<AccountingEntryType, String> {

    @Override
    public String convertToDatabaseColumn(AccountingEntryType attribute) {
        return attribute.key();
    }

    @Override
    public AccountingEntryType convertToEntityAttribute(String dbData) {
        return AccountingEntryType.fromKey(dbData);
    }
}
