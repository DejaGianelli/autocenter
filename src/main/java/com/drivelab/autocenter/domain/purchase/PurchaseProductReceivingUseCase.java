package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.accountingentry.AccountingCreditEntry;
import com.drivelab.autocenter.domain.accountingentry.AccountingDebitEntry;
import com.drivelab.autocenter.domain.accountingentry.AccountingEntryRepository;
import com.drivelab.autocenter.domain.financialaccount.FinancialAccountCategory;
import com.drivelab.autocenter.domain.financialaccount.FinancialAccountNotFoundException;
import com.drivelab.autocenter.domain.financialaccount.FinancialAccountRepository;
import com.drivelab.autocenter.domain.inventory.*;
import com.drivelab.autocenter.domain.product.ProductNotFoundException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.drivelab.autocenter.domain.inventory.InventoryMovementType.ENTRY;

@Service
@Transactional
public class PurchaseProductReceivingUseCase {

    private final PurchaseRepository purchaseRepository;
    private final FinancialAccountRepository financialAccountRepository;
    private final AccountingEntryRepository accountingEntryRepository;
    private final InventoryRepository inventoryRepository;
    private final InventoryMovementRepository inventoryMovementRepository;

    public PurchaseProductReceivingUseCase(PurchaseRepository purchaseRepository,
                                           FinancialAccountRepository financialAccountRepository,
                                           AccountingEntryRepository accountingEntryRepository,
                                           InventoryRepository inventoryRepository,
                                           InventoryMovementRepository inventoryMovementRepository) {
        this.purchaseRepository = purchaseRepository;
        this.financialAccountRepository = financialAccountRepository;
        this.accountingEntryRepository = accountingEntryRepository;
        this.inventoryRepository = inventoryRepository;
        this.inventoryMovementRepository = inventoryMovementRepository;
    }

    public void receiveProduct(@NonNull PurchaseProductReceivingCommand command) {
        Optional<Purchase> purchaseOptional = purchaseRepository.findByPublicIdLocked(command.purchaseId());
        if (purchaseOptional.isEmpty()) {
            throw new PurchaseNotFoundException(command.purchaseId());
        }
        Purchase purchase = purchaseOptional.get();

        PurchaseItem purchaseItem = purchase.item(command.productId())
                .orElseThrow(() -> new ProductNotFoundException(command.productId()));

        purchaseItem.receive(command.quantity());

        if (purchase.allProductsReceived()) {
            purchase.markAsReceived();
        }

        Inventory inventory = inventoryRepository.findByProductForUpdate(command.productId())
                .orElseThrow(() -> new InventoryForProductNotFoundException(command.productId()));

        inventory.update(ENTRY, command.quantity());
        inventoryRepository.save(inventory);

        InventoryMovement movement = new InventoryMovement(ENTRY, command.quantity(), inventory);
        inventoryMovementRepository.save(movement);

        FinancialAccountCategory category115 = new FinancialAccountCategory("1.1.5");
        if (!financialAccountRepository.existsByCategory(category115)) {
            throw new FinancialAccountNotFoundException(category115);
        }

        FinancialAccountCategory category113 = new FinancialAccountCategory("1.1.3");
        if (!financialAccountRepository.existsByCategory(category113)) {
            throw new FinancialAccountNotFoundException(category113);
        }

        // TODO: Extract Accounting double-entry logic to a class
        AccountingCreditEntry creditEntry = new AccountingCreditEntry(purchaseItem.totalReceived(), category115);
        accountingEntryRepository.save(creditEntry);

        AccountingDebitEntry debitEntry = new AccountingDebitEntry(purchaseItem.totalReceived(), category113);
        accountingEntryRepository.save(debitEntry);

        purchaseRepository.save(purchase);
    }
}
