package com.drivelab.autocenter.domain.serviceorder;

import com.drivelab.autocenter.domain.PublicId;
import jakarta.persistence.Embeddable;

@Embeddable
public class ServiceOrderPublicId extends PublicId {
    public ServiceOrderPublicId() {
        super();
    }

    public ServiceOrderPublicId(String value) {
        super(value);
    }
}
