package com.drivelab.autocenter.rest.vehicle;

import com.drivelab.autocenter.domain.InternalId;
import com.drivelab.autocenter.domain.customer.CustomerPublicId;
import com.drivelab.autocenter.domain.vehicle.Plate;
import com.drivelab.autocenter.domain.vehicle.VehicleCreationCommand;
import org.springframework.stereotype.Component;

@Component
public class VehicleCreationRequestBodyMapping {

    public VehicleCreationCommand command(VehicleCreationRequestBody requestBody) {
        return new VehicleCreationCommand(new Plate(requestBody.getPlate()),
                new InternalId(requestBody.getModelId()),
                new CustomerPublicId(requestBody.getCustomerId()));
    }
}
