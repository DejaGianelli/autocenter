package com.drivelab.autocenter.rest.supplier;

import org.springframework.lang.NonNull;

public class SupplierCreationResponseBody {

    private final String id;
    private final String cnpj;
    private final String name;

    public SupplierCreationResponseBody(@NonNull String id, @NonNull String cnpj, @NonNull String name) {
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
