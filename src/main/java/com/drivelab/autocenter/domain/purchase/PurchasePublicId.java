package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.PublicId;
import jakarta.persistence.Embeddable;

@Embeddable
public class PurchasePublicId extends PublicId {
    public PurchasePublicId() {
        super();
    }

    public PurchasePublicId(String value) {
        super(value);
    }
}
