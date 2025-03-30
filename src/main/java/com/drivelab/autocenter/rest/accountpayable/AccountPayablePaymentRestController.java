package com.drivelab.autocenter.rest.accountpayable;

import com.drivelab.autocenter.domain.accountpayable.AccountPayable;
import com.drivelab.autocenter.domain.accountpayable.AccountPayablePaymentCommand;
import com.drivelab.autocenter.domain.accountpayable.AccountPayablePaymentUseCase;
import com.drivelab.autocenter.domain.accountpayable.AccountPayablePublicId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/accounts-payable")
public class AccountPayablePaymentRestController {

    private final AccountPayablePaymentUseCase useCase;
    private final AccountPayablePaymentResponseMapping responseMapping;
    private final AccountPayablePaymentRequestMapping requestMapping;

    @Autowired
    public AccountPayablePaymentRestController(AccountPayablePaymentUseCase useCase,
                                               AccountPayablePaymentResponseMapping responseMapping,
                                               AccountPayablePaymentRequestMapping requestMapping) {
        this.useCase = useCase;
        this.responseMapping = responseMapping;
        this.requestMapping = requestMapping;
    }

    @PostMapping("/{id}")
    public ResponseEntity<AccountPayablePaymentResponseBody> response(@PathVariable String id,
                                                                      @RequestBody AccountPayablePaymentRequestBody requestBody) {
        AccountPayablePaymentCommand command = requestMapping.command(new AccountPayablePublicId(id), requestBody);
        AccountPayable payable = useCase.paid(command);
        AccountPayablePaymentResponseBody responseBody = responseMapping.responseBody(payable);
        return ResponseEntity.ok(responseBody);
    }
}
