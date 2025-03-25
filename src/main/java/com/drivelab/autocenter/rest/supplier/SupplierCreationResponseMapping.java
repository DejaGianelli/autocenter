package com.drivelab.autocenter.rest.supplier;

import com.drivelab.autocenter.domain.supplier.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierCreationResponseMapping {

    public SupplierCreationResponseBody responseBody(Supplier supplier) {
        return new SupplierCreationResponseBody(supplier.publicId().toString(), supplier.cnpj().toString(),
                supplier.name().toString());
    }
}
