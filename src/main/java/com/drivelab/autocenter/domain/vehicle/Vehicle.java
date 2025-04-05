package com.drivelab.autocenter.domain.vehicle;

import com.drivelab.autocenter.domain.DomainEntity;
import com.drivelab.autocenter.domain.customer.Customer;
import jakarta.persistence.*;

@AttributeOverrides({
        @AttributeOverride(name = "plate.value", column = @Column(name = "plate", nullable = false)),
        @AttributeOverride(name = "odometer.value", column = @Column(name = "odometer_value", nullable = false))
})
@Entity
@Table(name = "vehicles")
public class Vehicle extends DomainEntity {

    @Embedded
    private VehiclePublicId publicId;

    @Embedded
    private Plate plate;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private VehicleModel model;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    protected Vehicle() {
        //Public empty constructor needed by ORM
    }

    private Vehicle(Builder builder) {
        publicId = builder.publicId;
        plate = builder.plate;
        model = builder.model;
        customer = builder.customer;
    }

    public Plate plate() {
        return plate;
    }

    public VehicleModel model() {
        return model;
    }

    public VehiclePublicId publicId() {
        return publicId;
    }

    public Customer customer() {
        return customer;
    }

    public static final class Builder {
        private VehiclePublicId publicId;
        private Plate plate;
        private VehicleModel model;
        private Customer customer;

        private Builder() {
            this.publicId = new VehiclePublicId();
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder publicId(VehiclePublicId val) {
            publicId = val;
            return this;
        }

        public Builder plate(Plate val) {
            plate = val;
            return this;
        }

        public Builder model(VehicleModel val) {
            model = val;
            return this;
        }

        public Builder customer(Customer val) {
            customer = val;
            return this;
        }

        public Vehicle build() {
            return new Vehicle(this);
        }
    }
}
