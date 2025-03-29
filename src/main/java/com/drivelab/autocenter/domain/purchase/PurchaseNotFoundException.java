package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.EntityNotFoundException;

public class PurchaseNotFoundException extends EntityNotFoundException {
    public PurchaseNotFoundException(PurchasePublicId id) {
        super("Purchase of id " + id + " does not exist");
    }
}
