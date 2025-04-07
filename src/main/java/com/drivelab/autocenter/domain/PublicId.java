package com.drivelab.autocenter.domain;

import com.github.f4b6a3.ulid.Ulid;
import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.MappedSuperclass;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class PublicId implements Serializable {

    @Convert(converter = UlidAttributeConverter.class)
    @Column(name = "public_id", updatable = false)
    protected final Ulid value;

    public PublicId() {
        value = UlidCreator.getUlid();
    }

    public PublicId(@NonNull String value) {
        try {
            this.value = Ulid.from(value);
        } catch (IllegalArgumentException ex) {
            throw new DomainException("Malformed id. It must be an ULID", ex);
        }
    }

    @Override
    public String toString() {
        return value.toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PublicId publicId = (PublicId) o;
        return Objects.equals(value, publicId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
