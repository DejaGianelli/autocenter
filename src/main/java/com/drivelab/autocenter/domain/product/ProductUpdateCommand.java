package com.drivelab.autocenter.domain.product;

public class ProductUpdateCommand {

    private final ProductPublicId publicId;
    private final ProductName name;

    public ProductPublicId publicId() {
        return publicId;
    }

    public ProductName name() {
        return name;
    }

    private ProductUpdateCommand(Builder builder) {
        publicId = builder.publicId;
        name = builder.name;
    }

    public static final class Builder {
        private ProductPublicId publicId;
        private ProductName name;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder publicId(ProductPublicId val) {
            publicId = val;
            return this;
        }

        public Builder name(ProductName val) {
            name = val;
            return this;
        }

        public ProductUpdateCommand build() {
            return new ProductUpdateCommand(this);
        }
    }
}
