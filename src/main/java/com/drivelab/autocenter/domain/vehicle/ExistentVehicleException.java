package com.drivelab.autocenter.domain.vehicle;

import com.drivelab.autocenter.domain.DomainException;

public class ExistentVehicleException extends DomainException {
    public ExistentVehicleException(Plate plate) {
        super("Vehicle of plate " + plate + " already exists");
    }
}
