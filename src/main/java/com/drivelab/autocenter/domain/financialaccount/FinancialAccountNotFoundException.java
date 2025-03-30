package com.drivelab.autocenter.domain.financialaccount;

import com.drivelab.autocenter.domain.EntityNotFoundException;

public class FinancialAccountNotFoundException extends EntityNotFoundException {
    public FinancialAccountNotFoundException(FinancialAccountCategory category) {
        super("Financial Account of category " + category + " does not exist");
    }
}
