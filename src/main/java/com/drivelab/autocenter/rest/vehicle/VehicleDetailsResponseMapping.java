package com.drivelab.autocenter.rest.vehicle;

import com.drivelab.autocenter.domain.vehicle.Vehicle;
import com.drivelab.autocenter.rest.vehicle.VehicleDetailsResponseBody.Customer;
import com.drivelab.autocenter.rest.vehicle.VehicleDetailsResponseBody.Model;
import org.springframework.stereotype.Component;

@Component
public class VehicleDetailsResponseMapping {

    public VehicleDetailsResponseBody responseBody(Vehicle vehicle) {
        return VehicleDetailsResponseBody.Builder.builder()
                .id(vehicle.publicId().toString())
                .plate(vehicle.plate().toString())
                .model(new Model(vehicle.model().name().toString()))
                .customer(new Customer(vehicle.customer().document()))
                .build();
    }
}
