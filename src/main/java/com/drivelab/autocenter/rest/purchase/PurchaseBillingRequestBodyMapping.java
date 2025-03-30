package com.drivelab.autocenter.rest.purchase;

import com.drivelab.autocenter.domain.Money;
import com.drivelab.autocenter.domain.purchase.PurchaseBillingCommand;
import com.drivelab.autocenter.domain.purchase.PurchaseBillingCommand.Installment;
import com.drivelab.autocenter.domain.purchase.PurchasePublicId;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PurchaseBillingRequestBodyMapping {

    public PurchaseBillingCommand command(@NonNull PurchasePublicId id, @NonNull PurchaseBillingRequestBody requestBody) {
        return new PurchaseBillingCommand(id, installments(requestBody));
    }

    private List<Installment> installments(PurchaseBillingRequestBody requestBody) {
        return requestBody.getInstallments()
                .stream()
                .map(i -> new Installment(Money.create(i.getAmount()), i.getDueDate()))
                .collect(Collectors.toList());
    }
}
