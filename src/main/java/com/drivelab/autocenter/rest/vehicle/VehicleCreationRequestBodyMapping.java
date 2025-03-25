package com.drivelab.autocenter.rest.vehicle;

import com.drivelab.autocenter.domain.InternalId;
import com.drivelab.autocenter.domain.customer.CustomerPublicId;
import com.drivelab.autocenter.domain.vehicle.Odometer;
import com.drivelab.autocenter.domain.vehicle.Plate;
import com.drivelab.autocenter.domain.vehicle.VehicleCreationCommand;
import org.springframework.stereotype.Component;

@Component
public class VehicleCreationRequestBodyMapping {

    public VehicleCreationCommand command(VehicleCreationRequestBody requestBody) {
        return VehicleCreationCommand.Builder.builder()
                .plate(new Plate(requestBody.getPlate()))
                .modelId(new InternalId(requestBody.getModelId()))
                .customerId(new CustomerPublicId(requestBody.getCustomerId()))
                .odometer(new Odometer(requestBody.getOdometer()))
                .build();
    }
}
