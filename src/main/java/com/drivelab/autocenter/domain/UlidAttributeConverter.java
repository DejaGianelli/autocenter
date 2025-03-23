package com.drivelab.autocenter.domain;

import com.github.f4b6a3.ulid.Ulid;
import jakarta.persistence.AttributeConverter;

public class UlidAttributeConverter implements AttributeConverter<Ulid, String> {
    @Override
    public String convertToDatabaseColumn(Ulid ulid) {
        return ulid.toLowerCase();
    }

    @Override
    public Ulid convertToEntityAttribute(String s) {
        return Ulid.from(s);
    }
}
