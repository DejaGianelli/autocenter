package com.drivelab.autocenter.domain.service;

import com.drivelab.autocenter.domain.EntityNotFoundException;
import com.drivelab.autocenter.domain.purchase.PurchasePublicId;

public class ServiceNotFoundException extends EntityNotFoundException {
    public ServiceNotFoundException(ServicePublicId id) {
        super("Service of id " + id + " does not exist");
    }
}
