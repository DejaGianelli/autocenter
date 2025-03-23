package com.drivelab.autocenter.domain.vehicle;

import com.drivelab.autocenter.domain.DomainEntity;
import jakarta.persistence.*;

@AttributeOverrides({
        @AttributeOverride(name = "name.value", column = @Column(name = "name", nullable = false))
})
@Entity
@Table(name = "vehicle_brands")
public class VehicleBrand extends DomainEntity {

    @Embedded
    private VehicleBrandName name;

    protected VehicleBrand() {
        //Empty constructor needed by ORM
    }

    public VehicleBrandName name() {
        return name;
    }
}
