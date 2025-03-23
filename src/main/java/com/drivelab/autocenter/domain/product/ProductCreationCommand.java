package com.drivelab.autocenter.domain.product;

public class ProductCreationCommand {

    private final Sku sku;
    private final ProductName name;

    private ProductCreationCommand(Builder builder) {
        sku = builder.sku;
        name = builder.name;
    }

    public Sku sku() {
        return sku;
    }

    public ProductName name() {
        return name;
    }

    public static final class Builder {
        private Sku sku;
        private ProductName name;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder sku(Sku val) {
            sku = val;
            return this;
        }

        public Builder name(ProductName val) {
            name = val;
            return this;
        }

        public ProductCreationCommand build() {
            return new ProductCreationCommand(this);
        }
    }
}
