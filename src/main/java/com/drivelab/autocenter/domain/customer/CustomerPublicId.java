package com.drivelab.autocenter.domain.customer;

import com.drivelab.autocenter.domain.PublicId;
import jakarta.persistence.Embeddable;

@Embeddable
public class CustomerPublicId extends PublicId {
    public CustomerPublicId() {
        super();
    }

    public CustomerPublicId(String value) {
        super(value);
    }
}
