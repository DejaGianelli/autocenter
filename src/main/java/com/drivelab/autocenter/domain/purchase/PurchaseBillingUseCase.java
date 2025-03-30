package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.Money;
import com.drivelab.autocenter.domain.accountingentry.AccountingCreditEntry;
import com.drivelab.autocenter.domain.accountingentry.AccountingDebitEntry;
import com.drivelab.autocenter.domain.accountingentry.AccountingEntryRepository;
import com.drivelab.autocenter.domain.accountpayable.AccountPayable;
import com.drivelab.autocenter.domain.accountpayable.AccountPayableRepository;
import com.drivelab.autocenter.domain.accountpayable.PayableOrigin;
import com.drivelab.autocenter.domain.financialaccount.FinancialAccountCategory;
import com.drivelab.autocenter.domain.financialaccount.FinancialAccountNotFoundException;
import com.drivelab.autocenter.domain.financialaccount.FinancialAccountRepository;
import com.drivelab.autocenter.domain.purchase.PurchaseBillingCommand.Installment;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static com.drivelab.autocenter.domain.accountpayable.PayableOriginType.PURCHASE;

@Service
@Transactional
public class PurchaseBillingUseCase {

    private final PurchaseRepository purchaseRepository;
    private final AccountPayableRepository accountPayableRepository;
    private final FinancialAccountRepository financialAccountRepository;
    private final AccountingEntryRepository accountingEntryRepository;

    public PurchaseBillingUseCase(PurchaseRepository purchaseRepository,
                                  AccountPayableRepository accountPayableRepository,
                                  FinancialAccountRepository financialAccountRepository,
                                  AccountingEntryRepository accountingEntryRepository) {
        this.purchaseRepository = purchaseRepository;
        this.accountPayableRepository = accountPayableRepository;
        this.financialAccountRepository = financialAccountRepository;
        this.accountingEntryRepository = accountingEntryRepository;
    }

    public void billedPurchase(@NonNull PurchaseBillingCommand command) {
        Optional<Purchase> purchaseOptional = purchaseRepository.findByPublicIdLocked(command.purchaseId());
        if (purchaseOptional.isEmpty()) {
            throw new PurchaseNotFoundException(command.purchaseId());
        }
        Purchase purchase = purchaseOptional.get();
        purchase.markAsBilled();

        for (Installment installment : command.installments()) {
            Money amount = installment.amount();
            LocalDate dueDate = installment.dueDate();
            PayableOrigin origin = new PayableOrigin(purchase.publicId().toString(), PURCHASE);
            AccountPayable payable = AccountPayable.Builder.builder()
                    .amount(amount)
                    .dueDate(dueDate)
                    .origin(origin)
                    .build();
            accountPayableRepository.save(payable);

            FinancialAccountCategory category = new FinancialAccountCategory("2.1.1");
            if (!financialAccountRepository.existsByCategory(category)) {
                throw new FinancialAccountNotFoundException(category);
            }
            AccountingCreditEntry creditEntry = new AccountingCreditEntry(payable.amount(), category);
            accountingEntryRepository.save(creditEntry);
        }

        FinancialAccountCategory category = new FinancialAccountCategory("1.1.5");
        if (!financialAccountRepository.existsByCategory(category)) {
            throw new FinancialAccountNotFoundException(category);
        }
        AccountingDebitEntry debitEntry = new AccountingDebitEntry(purchase.totalItemsCost(), category);
        accountingEntryRepository.save(debitEntry);

        purchaseRepository.save(purchase);
    }
}
