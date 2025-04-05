package com.drivelab.autocenter.rest.serviceorder;

import com.drivelab.autocenter.domain.customer.CustomerPublicId;
import com.drivelab.autocenter.domain.serviceorder.ServiceOrderCheckInCommand;
import com.drivelab.autocenter.domain.serviceorder.ServiceOrderObservations;
import com.drivelab.autocenter.domain.vehicle.Odometer;
import com.drivelab.autocenter.domain.vehicle.VehiclePublicId;
import org.springframework.stereotype.Component;

@Component
public class ServiceOrderCheckInRequestBodyMapping {

    public ServiceOrderCheckInCommand command(ServiceOrderCheckInRequestBody requestBody) {
        return new ServiceOrderCheckInCommand(
                new CustomerPublicId(requestBody.getCustomerId()),
                new VehiclePublicId(requestBody.getVehicleId()),
                new Odometer(requestBody.getOdometer()))
                .setObservations(new ServiceOrderObservations(requestBody.getObservations()));
    }
}
