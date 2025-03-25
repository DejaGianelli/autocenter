package com.drivelab.autocenter.rest.supplier;

public class SupplierCreationResponseBody {

    private final String id;
    private final String cnpj;
    private final String name;

    public SupplierCreationResponseBody(String id, String cnpj, String name) {
        this.id = id;
        this.cnpj = cnpj;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getName() {
        return name;
    }
}
