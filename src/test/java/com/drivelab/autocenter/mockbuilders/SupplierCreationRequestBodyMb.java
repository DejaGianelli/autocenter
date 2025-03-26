package com.drivelab.autocenter.mockbuilders;

import com.drivelab.autocenter.rest.supplier.SupplierCreationRequestBody;

public class SupplierCreationRequestBodyMb {

    private String cnpj;
    private String name;

    public SupplierCreationRequestBodyMb() {
    }

    public SupplierCreationRequestBody defaultRequest() {
        setCnpj("02744280000178").setName("Empresa teste LTDA");
        return new SupplierCreationRequestBody(cnpj, name);
    }

    public String getCnpj() {
        return cnpj;
    }

    public SupplierCreationRequestBodyMb setCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public String getName() {
        return name;
    }

    public SupplierCreationRequestBodyMb setName(String name) {
        this.name = name;
        return this;
    }
}
