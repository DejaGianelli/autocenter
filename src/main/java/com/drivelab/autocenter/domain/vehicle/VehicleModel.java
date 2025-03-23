package com.drivelab.autocenter.domain.vehicle;

import com.drivelab.autocenter.domain.DomainEntity;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@AttributeOverrides({
        @AttributeOverride(name = "name.value", column = @Column(name = "name", nullable = false))
})
@Entity
@Table(name = "vehicle_models")
public class VehicleModel extends DomainEntity {

    @Embedded
    private VehicleModelName name;

    @JoinColumn(name = "brand_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private VehicleBrand brand;

    protected VehicleModel() {
        // Empty constructor needed by ORM
    }

    public VehicleModel(@NonNull VehicleModelName name, @NonNull VehicleBrand brand) {
        this.name = name;
        this.brand = brand;
    }

    public VehicleModelName name() {
        return name;
    }

    public VehicleBrand brand() {
        return brand;
    }
}
