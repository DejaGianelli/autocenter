package com.drivelab.autocenter.domain.supplier;

import com.drivelab.autocenter.domain.Cnpj;

public class SupplierCreationCommand {

    private final SupplierName supplierName;
    private final Cnpj cnpj;

    private SupplierCreationCommand(Builder builder) {
        supplierName = builder.supplierName;
        cnpj = builder.cnpj;
    }

    public SupplierName supplierName() {
        return supplierName;
    }

    public Cnpj cnpj() {
        return cnpj;
    }

    public static final class Builder {
        private SupplierName supplierName;
        private Cnpj cnpj;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder supplierName(SupplierName val) {
            supplierName = val;
            return this;
        }

        public Builder cnpj(Cnpj val) {
            cnpj = val;
            return this;
        }

        public SupplierCreationCommand build() {
            return new SupplierCreationCommand(this);
        }
    }
}
