package com.drivelab.autocenter.domain.product;

import com.drivelab.autocenter.domain.DomainEntity;
import com.drivelab.autocenter.domain.PublicId;
import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;
import org.springframework.lang.NonNull;

import java.util.Objects;

@AttributeOverrides({
        @AttributeOverride(name = "sku.value", column = @Column(name = "sku")),
        @AttributeOverride(name = "name.value", column = @Column(name = "name"))
})
@Entity
@Table(name = "products")
public class Product extends DomainEntity {

    @Embedded
    private ProductPublicId publicId;

    @NaturalId
    @Embedded
    private Sku sku;

    @Embedded
    private ProductName name;

    protected Product() {
        //Empty constructor needed by ORM
    }

    public Product(@NonNull Sku sku, @NonNull ProductName name) {
        this.publicId = new ProductPublicId();
        this.sku = sku;
        this.name = name;
    }

    public void update(ProductUpdateCommand command) {
        this.name = command.name();
    }

    public Sku sku() {
        return sku;
    }

    public ProductName name() {
        return name;
    }

    public PublicId publicId() {
        return publicId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(publicId, product.publicId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(publicId);
    }
}
