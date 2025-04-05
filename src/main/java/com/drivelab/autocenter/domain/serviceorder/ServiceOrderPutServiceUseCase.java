package com.drivelab.autocenter.domain.serviceorder;

import com.drivelab.autocenter.domain.service.Service;
import com.drivelab.autocenter.domain.service.ServiceNotFoundException;
import com.drivelab.autocenter.domain.service.ServiceRepository;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@org.springframework.stereotype.Service
@Transactional
public class ServiceOrderPutServiceUseCase {

    private final ServiceOrderRepository serviceOrderRepository;
    private final ServiceRepository serviceRepository;

    public ServiceOrderPutServiceUseCase(ServiceOrderRepository serviceOrderRepository, ServiceRepository serviceRepository) {
        this.serviceOrderRepository = serviceOrderRepository;
        this.serviceRepository = serviceRepository;
    }

    public ServiceOrder udpatedServiceOrder(@NonNull ServiceOrderPutServiceCommand command) {
        Optional<ServiceOrder> soOptional = serviceOrderRepository.findByPublicIdLocked(command.serviceOrderId());
        if (soOptional.isEmpty()) {
            throw new ServiceOrderNotFoundException(command.serviceOrderId());
        }
        ServiceOrder serviceOrder = soOptional.get();

        Optional<Service> serviceOptional = serviceRepository.findByPublicIdLocked(command.serviceId());
        if (serviceOptional.isEmpty()) {
            throw new ServiceNotFoundException(command.serviceId());
        }
        Service service = serviceOptional.get();

        serviceOrder.addItem(new ServiceOrderService(service, command.price()));

        return serviceOrderRepository.save(serviceOrder);
    }
}
