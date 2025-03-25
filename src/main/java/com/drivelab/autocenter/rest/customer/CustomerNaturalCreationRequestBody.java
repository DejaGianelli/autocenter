package com.drivelab.autocenter.rest.customer;

public class CustomerNaturalCreationRequestBody extends CustomerCreationRequestBody {

    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
