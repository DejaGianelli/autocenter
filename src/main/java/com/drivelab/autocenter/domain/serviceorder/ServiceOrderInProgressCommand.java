package com.drivelab.autocenter.domain.serviceorder;

import org.springframework.lang.NonNull;

public class ServiceOrderInProgressCommand {

    private final ServiceOrderPublicId serviceOrderId;

    public ServiceOrderInProgressCommand(@NonNull ServiceOrderPublicId serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public ServiceOrderPublicId serviceOrderId() {
        return serviceOrderId;
    }
}
