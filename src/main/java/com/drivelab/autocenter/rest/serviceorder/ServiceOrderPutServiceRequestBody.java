package com.drivelab.autocenter.rest.serviceorder;

import com.drivelab.autocenter.rest.ValidMoney;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class ServiceOrderPutServiceRequestBody {

    @NotNull
    @ValidMoney
    @Schema(description = "The price of the service", example = "100.55")
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
