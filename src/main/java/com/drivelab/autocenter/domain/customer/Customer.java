package com.drivelab.autocenter.domain.customer;

import com.drivelab.autocenter.domain.DomainEntity;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "customers")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Customer extends DomainEntity {

    @Embedded
    protected CustomerPublicId publicId;

    @Convert(converter = CustomerTypeAttributeConverter.class)
    @Column(insertable = false, updatable = false)
    protected CustomerType type;

    protected Customer() {
    }

    public Customer(@NonNull CustomerPublicId publicId, @NonNull CustomerType type) {
        this.publicId = publicId;
        this.type = type;
    }

    public abstract String document();

    public CustomerPublicId publicId() {
        return publicId;
    }

    public CustomerType type() {
        return type;
    }
}
