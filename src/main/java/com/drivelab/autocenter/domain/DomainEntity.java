package com.drivelab.autocenter.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DomainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long internalId;

    protected DomainEntity() {
    }

    final public InternalId internalId() {
        return internalId == null ? new NotGeneratedInternalId() : new InternalId(internalId);
    }
}
