package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.DomainException;

public class PurchaseInvalidStatusChangeException extends DomainException {
    public PurchaseInvalidStatusChangeException(PurchaseStatus from, PurchaseStatus to) {
        super("Purchase cannot move from " + from + " to " + to);
    }
}
