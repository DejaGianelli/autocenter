package com.drivelab.autocenter.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InternalId implements Serializable {

    @Column(name = "internal_id", updatable = false)
    protected Long value;

    public InternalId() {
        // public no arg constructor required by ORM
    }

    public InternalId(Long value) {
        this.value = value;
    }

    public Long value() {
        return value;
    }

    protected boolean generated() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        InternalId that = (InternalId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
