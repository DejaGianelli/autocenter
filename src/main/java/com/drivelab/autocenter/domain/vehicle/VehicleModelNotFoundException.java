package com.drivelab.autocenter.domain.vehicle;

import com.drivelab.autocenter.domain.EntityNotFoundException;
import com.drivelab.autocenter.domain.InternalId;

public class VehicleModelNotFoundException extends EntityNotFoundException {
    public VehicleModelNotFoundException(InternalId id) {
        super("Vehicle model of id " + id + " does not exist");
    }
}
