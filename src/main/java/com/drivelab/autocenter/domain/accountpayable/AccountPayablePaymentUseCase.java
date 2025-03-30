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

    public AccountPayable paid(@NonNull AccountPayablePaymentCommand command) {
        Optional<AccountPayable> optional = accountPayableRepository.findByPublicIdLocked(command.publicId());
        if (optional.isEmpty()) {
            throw new AccountPayableNotFoundException(command.publicId());
        }
        AccountPayable payable = optional.get();
        payable.pay(command.paymentMethod());
        payable = accountPayableRepository.save(payable);

        FinancialAccountCategory category211 = new FinancialAccountCategory("2.1.1");
        financialAccountRepository.findByCategory(category211)
                .orElseThrow(() -> new FinancialAccountNotFoundException(category211));

        AccountingDebitEntry debitEntry = new AccountingDebitEntry(payable.amount(), category211);
        accountingEntryRepository.save(debitEntry);

        FinancialAccountCategory category111 = new FinancialAccountCategory("1.1.1");
        financialAccountRepository.findByCategory(category111)
                .orElseThrow(() -> new FinancialAccountNotFoundException(category111));

        AccountingCreditEntry creditEntry = new AccountingCreditEntry(payable.amount(), category111);
        accountingEntryRepository.save(creditEntry);

        return payable;
    }
}
