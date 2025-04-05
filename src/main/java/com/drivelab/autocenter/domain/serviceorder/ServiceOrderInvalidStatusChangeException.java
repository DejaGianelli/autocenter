package com.drivelab.autocenter.domain.serviceorder;

import com.drivelab.autocenter.domain.DomainException;

public class ServiceOrderInvalidStatusChangeException extends DomainException {
    public ServiceOrderInvalidStatusChangeException(ServiceOrderStatus from, ServiceOrderStatus to) {
        super("Service Order cannot move from " + from + " to " + to);
    }
}
