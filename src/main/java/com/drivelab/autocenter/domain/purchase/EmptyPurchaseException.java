package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.DomainException;

public class EmptyPurchaseException extends DomainException {
    public EmptyPurchaseException(Purchase purchase) {
        super("This operation requires that purchase " + purchase.publicId() + " has items attached");
    }
}
