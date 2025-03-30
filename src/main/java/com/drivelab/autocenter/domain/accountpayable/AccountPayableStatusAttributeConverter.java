package com.drivelab.autocenter.domain.accountpayable;

import jakarta.persistence.AttributeConverter;

public class AccountPayableStatusAttributeConverter implements AttributeConverter<AccountsPayableStatus, String> {

    @Override
    public String convertToDatabaseColumn(AccountsPayableStatus attribute) {
        return attribute.key();
    }

    @Override
    public AccountsPayableStatus convertToEntityAttribute(String dbData) {
        return AccountsPayableStatus.fromKey(dbData);
    }
}
