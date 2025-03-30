package com.drivelab.autocenter.rest.accountpayable;

import com.drivelab.autocenter.domain.accountpayable.AccountPayable;
import com.drivelab.autocenter.domain.accountpayable.AccountPayablePaymentUseCase;
import com.drivelab.autocenter.domain.accountpayable.AccountPayablePublicId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/accounts-payable")
public class AccountPayablePaymentRestController {

    private final AccountPayablePaymentUseCase useCase;
    private final AccountPayablePaymentResponseMapping responseMapping;

    @Autowired
    public AccountPayablePaymentRestController(AccountPayablePaymentUseCase useCase,
                                               AccountPayablePaymentResponseMapping responseMapping) {
        this.useCase = useCase;
        this.responseMapping = responseMapping;
    }

    @PostMapping("/{id}")
    public ResponseEntity<AccountPayablePaymentResponseBody> response(@PathVariable String id) {
        AccountPayable payable = useCase.paid(new AccountPayablePublicId(id));
        AccountPayablePaymentResponseBody responseBody = responseMapping.responseBody(payable);
        return ResponseEntity.ok(responseBody);
    }
}
