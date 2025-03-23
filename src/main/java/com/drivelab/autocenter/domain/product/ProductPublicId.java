package com.drivelab.autocenter.domain.product;

import com.drivelab.autocenter.domain.PublicId;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProductPublicId extends PublicId {
    public ProductPublicId() {
        super();
    }

    public ProductPublicId(String value) {
        super(value);
    }
}
