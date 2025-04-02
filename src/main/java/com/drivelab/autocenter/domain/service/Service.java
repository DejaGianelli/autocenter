package com.drivelab.autocenter.domain.service;

import com.drivelab.autocenter.domain.DomainEntity;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Entity
@Table(name = "services")
@AttributeOverrides({
        @AttributeOverride(name = "name.value", column = @Column(name = "name", nullable = false))
})
public class Service extends DomainEntity {

    @Embedded
    private ServicePublicId publicId;

    @Convert(converter = ServiceNameAttributeConverter.class)
    private ServiceName name;

    private OffsetDateTime createdAtUtc;

    protected Service() {
        this.publicId = new ServicePublicId();
        this.createdAtUtc = Instant.now().atOffset(ZoneOffset.UTC);
    }

    public Service(@NonNull ServiceName name) {
        this();
        this.name = name;
    }

    public ServicePublicId publicId() {
        return publicId;
    }

    public ServiceName name() {
        return name;
    }

    public OffsetDateTime createdAtUtc() {
        return createdAtUtc;
    }
}
