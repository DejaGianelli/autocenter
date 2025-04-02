package com.drivelab.autocenter.rest.serviceorder;

import com.drivelab.autocenter.domain.serviceorder.ServiceOrder;
import org.springframework.stereotype.Component;

@Component
public class ServiceOrderCreationResponseMapping {

    public ServiceOrderCreationResponseBody responseBody(ServiceOrder serviceOrder) {
        ServiceOrderCreationResponseBody response = new ServiceOrderCreationResponseBody();
        if (serviceOrder.observations().isPresent()) {
            response.setDescription(serviceOrder.observations().get().getValue());
        }
        return response.setCreatedAtUtc(serviceOrder.createdAtUtc())
                .setId(serviceOrder.publicId().toString())
                .setStatus(serviceOrder.status().key());
    }
}
