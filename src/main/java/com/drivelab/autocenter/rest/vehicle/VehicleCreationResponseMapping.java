package com.drivelab.autocenter.rest.vehicle;

import com.drivelab.autocenter.domain.vehicle.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleCreationResponseMapping {

    public VehicleCreationResponseBody responseBody(Vehicle vehicle) {
        return new VehicleCreationResponseBody(vehicle.plate().toString(), vehicle.model().name().value());
    }
}
