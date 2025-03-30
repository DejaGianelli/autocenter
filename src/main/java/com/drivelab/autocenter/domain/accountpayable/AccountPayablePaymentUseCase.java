package com.drivelab.autocenter.domain.accountpayable;

import com.drivelab.autocenter.domain.accountingentry.AccountingCreditEntry;
import com.drivelab.autocenter.domain.accountingentry.AccountingDebitEntry;
import com.drivelab.autocenter.domain.accountingentry.AccountingEntryRepository;
import com.drivelab.autocenter.domain.financialaccount.FinancialAccountCategory;
import com.drivelab.autocenter.domain.financialaccount.FinancialAccountNotFoundException;
import com.drivelab.autocenter.domain.financialaccount.FinancialAccountRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AccountPayablePaymentUseCase {

    private final AccountPayableRepository accountPayableRepository;
    private final AccountingEntryRepository accountingEntryRepository;
    private final FinancialAccountRepository financialAccountRepository;

    public AccountPayablePaymentUseCase(AccountPayableRepository accountPayableRepository,
                                        AccountingEntryRepository accountingEntryRepository,
                                        FinancialAccountRepository financialAccountRepository) {
        this.accountPayableRepository = accountPayableRepository;
        this.accountingEntryRepository = accountingEntryRepository;
        this.financialAccountRepository = financialAccountRepository;
    }

    public AccountPayable paid(@NonNull AccountPayablePublicId id) {
        Optional<AccountPayable> optional = accountPayableRepository.findByPublicIdLocked(id);
        if (optional.isEmpty()) {
            throw new AccountPayableNotFoundException(id);
        }
        AccountPayable payable = optional.get();
        payable.pay();
        payable = accountPayableRepository.save(payable);

        FinancialAccountCategory category = new FinancialAccountCategory("1.1.5");
        financialAccountRepository.findByCategory(category)
                .orElseThrow(() -> new FinancialAccountNotFoundException(category));

        AccountingDebitEntry debitEntry = new AccountingDebitEntry(payable.amount(), category);
        accountingEntryRepository.save(debitEntry);

        AccountingCreditEntry creditEntry = new AccountingCreditEntry(payable.amount(), category);
        accountingEntryRepository.save(creditEntry);

        return payable;
    }
}
