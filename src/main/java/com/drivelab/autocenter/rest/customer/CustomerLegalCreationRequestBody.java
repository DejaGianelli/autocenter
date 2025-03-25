package com.drivelab.autocenter.rest.customer;

public class CustomerLegalCreationRequestBody extends CustomerCreationRequestBody {

    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
