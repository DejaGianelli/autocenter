package com.drivelab.autocenter.domain.vehicle;

import com.drivelab.autocenter.domain.EntityNotFoundException;

public class VehicleNotFoundException extends EntityNotFoundException {
    public VehicleNotFoundException(VehiclePublicId id) {
        super("Vehicle of id " + id + " does not exist");
    }
}
