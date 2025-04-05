package com.drivelab.autocenter.rest.serviceorder;

import com.drivelab.autocenter.domain.serviceorder.ServiceOrder;
import com.drivelab.autocenter.rest.serviceorder.ServiceOrderPutServiceResponseBody.Service;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceOrderPutServiceResponseMapping {
    public ServiceOrderPutServiceResponseBody responseBody(@NonNull ServiceOrder serviceOrder) {
        return new ServiceOrderPutServiceResponseBody(serviceOrder.publicId().toString(),
                serviceOrder.total().decimal(),
                services(serviceOrder));
    }

    private List<Service> services(@NonNull ServiceOrder serviceOrder) {
        return serviceOrder.services()
                .stream()
                .map(s -> new Service(s.price().decimal(), s.service().publicId().toString()))
                .collect(Collectors.toList());
    }
}
