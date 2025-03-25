package com.drivelab.autocenter.domain.supplier;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class SupplierCreationUseCase {

    private final SupplierRepository supplierRepository;

    public SupplierCreationUseCase(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier newSupplier(SupplierCreationCommand command) {
        Optional<Supplier> optional = supplierRepository.findByCnpj(command.cnpj());
        if (optional.isPresent()) {
            throw new ExistentSupplierException(command.cnpj());
        }
        Supplier supplier = new Supplier(command.cnpj(), command.supplierName());
        supplier = supplierRepository.saveAndFlush(supplier);

        return supplier;
    }
}
