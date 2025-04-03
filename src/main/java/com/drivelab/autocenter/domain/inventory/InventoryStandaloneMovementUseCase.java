package com.drivelab.autocenter.domain.inventory;

import com.drivelab.autocenter.domain.Money;
import com.drivelab.autocenter.domain.accountingentry.AccountingCreditEntry;
import com.drivelab.autocenter.domain.accountingentry.AccountingDebitEntry;
import com.drivelab.autocenter.domain.accountingentry.AccountingEntryRepository;
import com.drivelab.autocenter.domain.financialaccount.FinancialAccountCategory;
import com.drivelab.autocenter.domain.financialaccount.FinancialAccountNotFoundException;
import com.drivelab.autocenter.domain.financialaccount.FinancialAccountRepository;
import com.drivelab.autocenter.domain.product.Product;
import com.drivelab.autocenter.domain.product.ProductNotFoundException;
import com.drivelab.autocenter.domain.product.ProductRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * A Standalone Inventory Movement represents a increase or decrease of a product inventory that was not generated
 * by a purchase, service order or a sale
 */
@Service
@Transactional
public class InventoryStandaloneMovementUseCase {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final InventoryMovementRepository inventoryMovementRepository;
    private final FinancialAccountRepository financialAccountRepository;
    private final AccountingEntryRepository accountingEntryRepository;

    public InventoryStandaloneMovementUseCase(InventoryRepository inventoryRepository,
                                              ProductRepository productRepository,
                                              InventoryMovementRepository inventoryMovementRepository,
                                              FinancialAccountRepository financialAccountRepository,
                                              AccountingEntryRepository accountingEntryRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.inventoryMovementRepository = inventoryMovementRepository;
        this.financialAccountRepository = financialAccountRepository;
        this.accountingEntryRepository = accountingEntryRepository;
    }

    public void movement(@NonNull InventoryMovementCommand command) {
        Optional<Product> optional = productRepository.findByPublicId(command.publicId());
        if (optional.isEmpty()) {
            throw new ProductNotFoundException(command.publicId());
        }
        Product product = optional.get();

        Inventory inventory = inventoryRepository.findByProductForUpdate(product.publicId())
                .orElseThrow(() -> new InventoryForProductNotFoundException(product.publicId()));

        inventory.update(command.type(), command.quantity());
        inventoryRepository.save(inventory);

        InventoryMovement movement = new InventoryMovement(command.type(), command.quantity(), inventory);
        inventoryMovementRepository.save(movement);

        Money total = Money.create(command.cost().cents() * command.quantity().value());

        FinancialAccountCategory account113 = new FinancialAccountCategory("1.1.3");
        if (!financialAccountRepository.existsByCategory(account113)) {
            throw new FinancialAccountNotFoundException(account113);
        }

        FinancialAccountCategory account3 = new FinancialAccountCategory("3");
        if (!financialAccountRepository.existsByCategory(account3)) {
            throw new FinancialAccountNotFoundException(account3);
        }

        switch (command.type()) {
            case ENTRY:
                // TODO: Extract Accounting double-entry logic to a class
                accountingEntryRepository.save(new AccountingDebitEntry(total, account113));
                accountingEntryRepository.save(new AccountingCreditEntry(total, account3));
                break;

            case WITHDRAW:
                // TODO: Extract Accounting double-entry logic to a class
                accountingEntryRepository.save(new AccountingCreditEntry(total, account113));
                accountingEntryRepository.save(new AccountingDebitEntry(total, account3));
                break;
        }
    }
}
