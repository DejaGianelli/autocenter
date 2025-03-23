package com.drivelab.autocenter.domain.product;

import com.drivelab.autocenter.domain.EntityNotFoundException;

public class ProductNotFoundException extends EntityNotFoundException {
    public ProductNotFoundException(ProductPublicId id) {
        super("Product of id " + id + " does not exist");
    }
}
