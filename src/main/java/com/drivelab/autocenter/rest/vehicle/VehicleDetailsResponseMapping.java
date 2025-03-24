package com.drivelab.autocenter.rest.vehicle;

import com.drivelab.autocenter.domain.vehicle.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleDetailsResponseMapping {

    public VehicleDetailsResponseBody responseBody(Vehicle vehicle) {
        return new VehicleDetailsResponseBody(vehicle.publicId().toString(),
                vehicle.plate().toString(),
                new VehicleDetailsResponseBody.Model(vehicle.model().name().toString()));
    }
}
