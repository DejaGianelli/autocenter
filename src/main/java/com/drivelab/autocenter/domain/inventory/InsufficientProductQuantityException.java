package com.drivelab.autocenter.domain.inventory;

import com.drivelab.autocenter.domain.DomainException;
import com.drivelab.autocenter.domain.PublicId;

public class InsufficientProductQuantityException extends DomainException {

    private final PublicId publicId;
    private final InventoryQuantity current;
    private final InventoryQuantity quantity;

    public PublicId publicId() {
        return publicId;
    }

    public InventoryQuantity current() {
        return current;
    }

    public InventoryQuantity quantity() {
        return quantity;
    }

    private InsufficientProductQuantityException(Builder builder) {
        super(builder.message(), builder.cause);
        publicId = builder.publicId;
        current = builder.current;
        quantity = builder.quantity;
    }

    public static final class Builder {
        private PublicId publicId;
        private InventoryQuantity current;
        private InventoryQuantity quantity;
        private Throwable cause;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder cause(Throwable val) {
            cause = val;
            return this;
        }

        public Builder publicId(PublicId val) {
            publicId = val;
            return this;
        }

        public Builder current(InventoryQuantity val) {
            current = val;
            return this;
        }

        public Builder quantity(InventoryQuantity val) {
            quantity = val;
            return this;
        }

        private String message() {
            return "Insufficient inventory for product " + publicId + ". Current stock " + current + ", " +
                    "requested quantity: " + quantity + ". Please check the stock or adjust the quantity";
        }

        public InsufficientProductQuantityException build() {
            return new InsufficientProductQuantityException(this);
        }
    }
}
