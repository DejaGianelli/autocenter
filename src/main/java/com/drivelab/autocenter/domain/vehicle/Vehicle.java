package com.drivelab.autocenter.domain.vehicle;

import com.drivelab.autocenter.domain.DomainEntity;
import com.drivelab.autocenter.domain.customer.Customer;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@AttributeOverrides({
        @AttributeOverride(name = "plate.value", column = @Column(name = "plate", nullable = false))
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

    public Vehicle(@NonNull Plate plate, @NonNull VehicleModel model, Customer customer) {
        this.customer = customer;
        this.publicId = new VehiclePublicId();
        this.plate = plate;
        this.model = model;
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
}
