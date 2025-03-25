package com.drivelab.autocenter.rest.customer;

public class CustomerCreationResponseBody {

    private String id;
    private String type;

    public CustomerCreationResponseBody(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
