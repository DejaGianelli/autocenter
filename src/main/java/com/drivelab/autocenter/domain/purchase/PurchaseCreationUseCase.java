package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.supplier.Supplier;
import com.drivelab.autocenter.domain.supplier.SupplierNotFoundException;
import com.drivelab.autocenter.domain.supplier.SupplierRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PurchaseCreationUseCase {

    private final PurchaseRepository purchaseRepository;
    private final SupplierRepository supplierRepository;

    public PurchaseCreationUseCase(PurchaseRepository purchaseRepository, SupplierRepository supplierRepository) {
        this.purchaseRepository = purchaseRepository;
        this.supplierRepository = supplierRepository;
    }

    public Purchase newPurchase(@NonNull PurchaseCreationCommand command) {
        Optional<Supplier> optional = supplierRepository.findByPublicId(command.supplierId());
        if (optional.isEmpty()) {
            throw new SupplierNotFoundException(command.supplierId());
        }
        Supplier supplier = optional.get();
        Purchase purchase = new Purchase(supplier);
        purchase = purchaseRepository.saveAndFlush(purchase);
        return purchase;
    }
}
