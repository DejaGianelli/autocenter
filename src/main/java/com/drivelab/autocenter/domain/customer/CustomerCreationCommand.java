package com.drivelab.autocenter.domain.customer;

import com.drivelab.autocenter.domain.Cnpj;
import com.drivelab.autocenter.domain.Cpf;

public class CustomerCreationCommand {

    private Cnpj cnpj;
    private Cpf cpf;
    private CustomerType type;

    private CustomerCreationCommand(Builder builder) {
        cnpj = builder.cnpj;
        cpf = builder.cpf;
        type = builder.type;
    }

    public Cnpj cnpj() {
        return cnpj;
    }

    public Cpf cpf() {
        return cpf;
    }

    public CustomerType type() {
        return type;
    }

    public static final class Builder {
        private Cnpj cnpj;
        private Cpf cpf;
        private CustomerType type;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder type(CustomerType val) {
            type = val;
            return this;
        }

        public Builder cnpj(Cnpj val) {
            cnpj = val;
            return this;
        }

        public Builder cpf(Cpf val) {
            cpf = val;
            return this;
        }

        public CustomerCreationCommand build() {
            return new CustomerCreationCommand(this);
        }
    }
}
