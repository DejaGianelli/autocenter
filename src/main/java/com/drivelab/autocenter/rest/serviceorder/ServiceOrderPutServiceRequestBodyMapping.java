package com.drivelab.autocenter.rest.serviceorder;

import com.drivelab.autocenter.domain.Money;
import com.drivelab.autocenter.domain.service.ServicePublicId;
import com.drivelab.autocenter.domain.serviceorder.ServiceOrderPublicId;
import com.drivelab.autocenter.domain.serviceorder.ServiceOrderPutServiceCommand;
import org.springframework.stereotype.Component;

@Component
public class ServiceOrderPutServiceRequestBodyMapping {

    public ServiceOrderPutServiceCommand command(ServiceOrderPublicId serviceOrderId,
                                                 ServicePublicId productId,
                                                 ServiceOrderPutServiceRequestBody requestBody) {
        return new ServiceOrderPutServiceCommand(serviceOrderId, productId, Money.create(requestBody.getPrice()));
    }
}
