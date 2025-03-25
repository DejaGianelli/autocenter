package com.drivelab.autocenter.rest.supplier;

import com.drivelab.autocenter.domain.Cnpj;
import com.drivelab.autocenter.domain.supplier.SupplierCreationCommand;
import com.drivelab.autocenter.domain.supplier.SupplierName;
import org.springframework.stereotype.Component;

@Component
public class SupplierCreationRequestBodyMapping {

    public SupplierCreationCommand command(SupplierCreationRequestBody requestBody) {
        return SupplierCreationCommand.Builder
                .builder()
                .supplierName(new SupplierName(requestBody.getName()))
                .cnpj(new Cnpj(requestBody.getCnpj()))
                .build();
    }
}
