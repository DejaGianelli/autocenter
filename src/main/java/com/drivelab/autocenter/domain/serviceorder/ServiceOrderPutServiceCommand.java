package com.drivelab.autocenter.domain.serviceorder;

import com.drivelab.autocenter.domain.Money;
import com.drivelab.autocenter.domain.service.ServicePublicId;
import org.springframework.lang.NonNull;

public class ServiceOrderPutServiceCommand {

    private final ServiceOrderPublicId serviceOrderId;
    private final ServicePublicId serviceId;
    private final Money price;

    public ServiceOrderPutServiceCommand(@NonNull ServiceOrderPublicId serviceOrderId,
                                         @NonNull ServicePublicId serviceId,
                                         @NonNull Money price) {
        this.serviceOrderId = serviceOrderId;
        this.serviceId = serviceId;
        this.price = price;
    }

    public ServiceOrderPublicId serviceOrderId() {
        return serviceOrderId;
    }

    public ServicePublicId serviceId() {
        return serviceId;
    }

    public Money price() {
        return price;
    }
}
