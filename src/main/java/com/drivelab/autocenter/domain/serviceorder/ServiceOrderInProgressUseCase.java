package com.drivelab.autocenter.domain.serviceorder;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ServiceOrderInProgressUseCase {

    private final ServiceOrderRepository serviceOrderRepository;

    public ServiceOrderInProgressUseCase(ServiceOrderRepository serviceOrderRepository) {
        this.serviceOrderRepository = serviceOrderRepository;
    }

    public void putServiceOrderInProgress(@NonNull ServiceOrderInProgressCommand command) {
        Optional<ServiceOrder> optional = serviceOrderRepository.findByPublicId(command.serviceOrderId());
        if (optional.isEmpty()) {
            throw new ServiceOrderNotFoundException(command.serviceOrderId());
        }
        ServiceOrder serviceOrder = optional.get();
        serviceOrder.moveToInProgress();
        serviceOrderRepository.save(serviceOrder);
    }
}
