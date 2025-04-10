package com.drivelab.autocenter.rest.serviceorder;

import com.drivelab.autocenter.domain.serviceorder.ServiceOrderObservations;
import com.drivelab.autocenter.rest.ValidPublicId;
import com.drivelab.autocenter.rest.vehicle.ValidOdometer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ServiceOrderCheckInRequestBody {

    @ValidPublicId
    @Schema(description = "The id of the customer", example = "01JRENW23TQHFX5S0ABH4VB9Z7")
    private String customerId;

    @ValidPublicId
    @Schema(description = "The id of the vehicle", example = "01JRENW23TQHFX5S0ABH4VB9Z7")
    private String vehicleId;

    @Schema(description = "Some observation about the Service Order", example = "Some observation text")
    @Size(max = ServiceOrderObservations.MAX_SIZE)
    private String observations;

    @Schema(description = "Odometer value of the vehicle in CheckIn", example = "123456")
    @NotNull
    @ValidOdometer
    private Integer odometer;

    public Integer getOdometer() {
        return odometer;
    }

    public ServiceOrderCheckInRequestBody setOdometer(Integer odometer) {
        this.odometer = odometer;
        return this;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public ServiceOrderCheckInRequestBody setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
