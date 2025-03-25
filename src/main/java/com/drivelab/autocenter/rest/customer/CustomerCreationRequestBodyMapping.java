package com.drivelab.autocenter.rest.customer;

import com.drivelab.autocenter.domain.Cnpj;
import com.drivelab.autocenter.domain.Cpf;
import com.drivelab.autocenter.domain.customer.CustomerCreationCommand;
import com.drivelab.autocenter.domain.customer.CustomerType;
import org.springframework.stereotype.Component;

@Component
public class CustomerCreationRequestBodyMapping {

    public CustomerCreationCommand command(CustomerCreationRequestBody requestBody) {
        CustomerType type = CustomerType.fromKey(requestBody.getType());
        CustomerCreationCommand.Builder builder = CustomerCreationCommand.Builder.builder();
        builder.type(type);
        switch (type) {
            case LEGAL -> {
                CustomerLegalCreationRequestBody req = (CustomerLegalCreationRequestBody) requestBody;
                builder.cnpj(new Cnpj(req.getCnpj()));
            }
            case NATURAL -> {
                CustomerNaturalCreationRequestBody req = (CustomerNaturalCreationRequestBody) requestBody;
                builder.cpf(new Cpf(req.getCpf()));
            }
            default -> {
                throw new IllegalArgumentException("Customer type " + requestBody.getType() + " not implemented");
            }
        }
        return builder.build();
    }
}
