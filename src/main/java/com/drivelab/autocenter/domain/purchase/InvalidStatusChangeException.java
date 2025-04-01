package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.DomainException;

public class InvalidStatusChangeException extends DomainException {
    public InvalidStatusChangeException(PurchaseStatus from, PurchaseStatus to) {
        super("Purchase cannot move from " + from + " to " + to);
    }
}
