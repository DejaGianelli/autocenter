package com.drivelab.autocenter.domain.accountpayable;

import com.drivelab.autocenter.domain.PublicId;
import jakarta.persistence.Embeddable;

@Embeddable
public class AccountPayablePublicId extends PublicId {
    public AccountPayablePublicId() {
        super();
    }

    public AccountPayablePublicId(String value) {
        super(value);
    }
}
