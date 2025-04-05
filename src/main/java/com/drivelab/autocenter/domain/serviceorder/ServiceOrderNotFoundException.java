package com.drivelab.autocenter.domain.serviceorder;

import com.drivelab.autocenter.domain.EntityNotFoundException;

public class ServiceOrderNotFoundException extends EntityNotFoundException {
    public ServiceOrderNotFoundException(ServiceOrderPublicId id) {
        super("Service Order of id " + id + " does not exist");
    }
}
