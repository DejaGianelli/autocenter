package com.drivelab.autocenter.domain.vehicle;

import com.drivelab.autocenter.domain.PublicId;
import jakarta.persistence.Embeddable;

@Embeddable
public class VehiclePublicId extends PublicId {
    public VehiclePublicId() {
        super();
    }

    public VehiclePublicId(String value) {
        super(value);
    }
}
