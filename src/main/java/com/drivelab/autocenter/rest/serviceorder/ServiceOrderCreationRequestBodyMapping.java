package com.drivelab.autocenter.rest.serviceorder;

import com.drivelab.autocenter.domain.customer.CustomerPublicId;
import com.drivelab.autocenter.domain.serviceorder.ServiceOrderCreationCommand;
import com.drivelab.autocenter.domain.serviceorder.ServiceOrderObservations;
import com.drivelab.autocenter.domain.vehicle.VehiclePublicId;
import org.springframework.stereotype.Component;

@Component
public class ServiceOrderCreationRequestBodyMapping {

    public ServiceOrderCreationCommand command(ServiceOrderCreationRequestBody requestBody) {
        return new ServiceOrderCreationCommand(
                new CustomerPublicId(requestBody.getCustomerId()),
                new VehiclePublicId(requestBody.getVehicleId())
        ).setObservations(new ServiceOrderObservations(requestBody.getObservations()));
    }
}
