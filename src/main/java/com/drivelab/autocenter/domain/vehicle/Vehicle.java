package com.drivelab.autocenter.domain.vehicle;

import com.drivelab.autocenter.domain.DomainEntity;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@AttributeOverrides({
        @AttributeOverride(name = "plate.value", column = @Column(name = "plate", nullable = false))
})
@Entity
@Table(name = "vehicles")
public class Vehicle extends DomainEntity {

    @Embedded
    private Plate plate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "model_id")
    private VehicleModel model;

    protected Vehicle() {
        //Public empty constructor needed by ORM
    }

    public Vehicle(@NonNull Plate plate, @NonNull VehicleModel model) {
        this.plate = plate;
        this.model = model;
    }

    public Plate plate() {
        return plate;
    }

    public VehicleModel model() {
        return model;
    }
}
