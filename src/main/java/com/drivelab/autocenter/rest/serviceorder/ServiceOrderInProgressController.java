package com.drivelab.autocenter.rest.serviceorder;

import com.drivelab.autocenter.domain.serviceorder.ServiceOrderInProgressCommand;
import com.drivelab.autocenter.domain.serviceorder.ServiceOrderInProgressUseCase;
import com.drivelab.autocenter.domain.serviceorder.ServiceOrderPublicId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/service-orders/{serviceOrderId}/in-progress")
public class ServiceOrderInProgressController implements ServiceOrderInProgressApi {

    private final ServiceOrderInProgressUseCase useCase;

    @Autowired
    public ServiceOrderInProgressController(ServiceOrderInProgressUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public ResponseEntity<Void> response(String serviceOrderId) {
        ServiceOrderInProgressCommand command = new ServiceOrderInProgressCommand(new ServiceOrderPublicId(serviceOrderId));
        useCase.putServiceOrderInProgress(command);
        return ResponseEntity.ok().build();
    }
}
