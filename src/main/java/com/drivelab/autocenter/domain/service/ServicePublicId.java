package com.drivelab.autocenter.domain.service;

import com.drivelab.autocenter.domain.PublicId;
import jakarta.persistence.Embeddable;

@Embeddable
public class ServicePublicId extends PublicId {
    public ServicePublicId() {
        super();
    }

    public ServicePublicId(String value) {
        super(value);
    }
}
