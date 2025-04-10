package com.drivelab.autocenter.rest.serviceorder;

import com.drivelab.autocenter.domain.service.ServicePublicId;
import com.drivelab.autocenter.domain.serviceorder.ServiceOrder;
import com.drivelab.autocenter.domain.serviceorder.ServiceOrderPublicId;
import com.drivelab.autocenter.domain.serviceorder.ServiceOrderPutServiceCommand;
import com.drivelab.autocenter.domain.serviceorder.ServiceOrderPutServiceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/service-orders/{serviceOrderId}/services/{serviceId}")
public class ServiceOrderPutServiceRestController implements ServiceOrderPutServiceRestApi {

    private final ServiceOrderPutServiceUseCase useCase;
    private final ServiceOrderPutServiceRequestBodyMapping requestMapping;
    private final ServiceOrderPutServiceResponseMapping responseMapping;

    @Autowired
    public ServiceOrderPutServiceRestController(ServiceOrderPutServiceUseCase useCase,
                                                ServiceOrderPutServiceRequestBodyMapping requestMapping,
                                                ServiceOrderPutServiceResponseMapping responseMapping) {
        this.useCase = useCase;
        this.requestMapping = requestMapping;
        this.responseMapping = responseMapping;
    }

    @Override
    public ResponseEntity<ServiceOrderPutServiceResponseBody> response(String serviceOrderId,
                                                                       String serviceId,
                                                                       ServiceOrderPutServiceRequestBody requestBody) {
        ServiceOrderPutServiceCommand command = requestMapping.command(new ServiceOrderPublicId(serviceOrderId),
                new ServicePublicId(serviceId), requestBody);
        ServiceOrder serviceOrder = useCase.udpatedServiceOrder(command);
        ServiceOrderPutServiceResponseBody responseBody = responseMapping.responseBody(serviceOrder);
        return ResponseEntity.ok(responseBody);
    }
}
