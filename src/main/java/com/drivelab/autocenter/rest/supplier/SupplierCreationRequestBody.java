package com.drivelab.autocenter.rest.supplier;

public class SupplierCreationRequestBody {
    private String cnpj;
    private String name;

    public SupplierCreationRequestBody() {
    }

    public SupplierCreationRequestBody(String cnpj, String name) {
        this.cnpj = cnpj;
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getName() {
        return name;
    }
}
