package com.drivelab.autocenter.rest.customer;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        visible = true,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CustomerNaturalCreationRequestBody.class, name = "natural"),
        @JsonSubTypes.Type(value = CustomerLegalCreationRequestBody.class, name = "legal")
})
public abstract class CustomerCreationRequestBody {

    protected String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
