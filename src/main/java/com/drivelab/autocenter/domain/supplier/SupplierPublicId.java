package com.drivelab.autocenter.domain.supplier;

import com.drivelab.autocenter.domain.PublicId;
import jakarta.persistence.Embeddable;

@Embeddable
public class SupplierPublicId extends PublicId {
    public SupplierPublicId() {
        super();
    }

    public SupplierPublicId(String value) {
        super(value);
    }
}
