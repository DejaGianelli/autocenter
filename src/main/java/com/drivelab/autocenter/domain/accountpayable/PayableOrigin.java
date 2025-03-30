package com.drivelab.autocenter.domain.accountpayable;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import org.springframework.lang.NonNull;

@Embeddable
public class PayableOrigin {

    @Column(name = "origin_id")
    private String id;

    @Convert(converter = PayableOriginTypeAttributeConverter.class)
    @Column(name = "origin_type")
    private PayableOriginType type;

    protected PayableOrigin() {
    }

    public PayableOrigin(@NonNull String id, @NonNull PayableOriginType type) {
        this.id = id;
        this.type = type;
    }

    public String id() {
        return id;
    }

    public PayableOriginType type() {
        return type;
    }
}
