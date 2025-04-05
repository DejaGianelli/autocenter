package com.drivelab.autocenter.rest.serviceorder;

import com.drivelab.autocenter.domain.serviceorder.ServiceOrder;
import org.springframework.stereotype.Component;

@Component
public class ServiceOrderCheckInResponseMapping {

    public ServiceOrderCheckInResponseBody responseBody(ServiceOrder serviceOrder) {
        ServiceOrderCheckInResponseBody response = new ServiceOrderCheckInResponseBody();
        if (serviceOrder.observations().isPresent()) {
            response.setDescription(serviceOrder.observations().get().getValue());
        }
        return response.setCreatedAtUtc(serviceOrder.createdAtUtc())
                .setId(serviceOrder.publicId().toString())
                .setStatus(serviceOrder.status().key());
    }
}
