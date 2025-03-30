package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.accountingentry.AccountingCreditEntry;
import com.drivelab.autocenter.domain.accountingentry.AccountingDebitEntry;
import com.drivelab.autocenter.domain.accountingentry.AccountingEntryRepository;
import com.drivelab.autocenter.domain.financialaccount.FinancialAccountCategory;
import com.drivelab.autocenter.domain.financialaccount.FinancialAccountNotFoundException;
import com.drivelab.autocenter.domain.financialaccount.FinancialAccountRepository;
import com.drivelab.autocenter.domain.inventory.InventoryMovementUseCase;
import com.drivelab.autocenter.domain.inventory.InventoryQuantity;
import com.drivelab.autocenter.domain.product.ProductNotFoundException;
import com.drivelab.autocenter.domain.purchase.PurchaseGoodsReceivingCommand.Item;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.drivelab.autocenter.domain.inventory.InventoryMovementType.ENTRY;

@Service
@Transactional
public class PurchaseGoodReceivingUseCase {

    private final PurchaseRepository purchaseRepository;
    private final InventoryMovementUseCase inventoryMovement;
    private final FinancialAccountRepository financialAccountRepository;
    private final AccountingEntryRepository accountingEntryRepository;

    public PurchaseGoodReceivingUseCase(PurchaseRepository purchaseRepository,
                                        InventoryMovementUseCase inventoryMovement,
                                        FinancialAccountRepository financialAccountRepository,
                                        AccountingEntryRepository accountingEntryRepository) {
        this.purchaseRepository = purchaseRepository;
        this.inventoryMovement = inventoryMovement;
        this.financialAccountRepository = financialAccountRepository;
        this.accountingEntryRepository = accountingEntryRepository;
    }

    public void goodsReceived(@NonNull PurchaseGoodsReceivingCommand command) {
        Optional<Purchase> purchaseOptional = purchaseRepository.findByPublicIdLocked(command.purchaseId());
        if (purchaseOptional.isEmpty()) {
            throw new PurchaseNotFoundException(command.purchaseId());
        }
        Purchase purchase = purchaseOptional.get();
        purchase.markAsReceived();

        for (Item item : command.items()) {
            PurchaseItem purchaseItem = purchase.item(item.productId())
                    .orElseThrow(() -> new ProductNotFoundException(item.productId()));
            purchaseItem.receive(item.quantity());
            inventoryMovement.movement(purchaseItem.product(), ENTRY, new InventoryQuantity(item.quantity()));
        }

        FinancialAccountCategory category115 = new FinancialAccountCategory("1.1.5");
        if (!financialAccountRepository.existsByCategory(category115)) {
            throw new FinancialAccountNotFoundException(category115);
        }
        AccountingCreditEntry creditEntry = new AccountingCreditEntry(purchase.totalItemsReceivedCost(), category115);
        accountingEntryRepository.save(creditEntry);

        FinancialAccountCategory category113 = new FinancialAccountCategory("1.1.3");
        if (!financialAccountRepository.existsByCategory(category113)) {
            throw new FinancialAccountNotFoundException(category113);
        }
        AccountingDebitEntry debitEntry = new AccountingDebitEntry(purchase.totalItemsReceivedCost(), category113);
        accountingEntryRepository.save(debitEntry);

        purchaseRepository.save(purchase);
    }
}
