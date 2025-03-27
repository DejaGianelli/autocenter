package com.drivelab.autocenter.domain.supplier;

import com.drivelab.autocenter.domain.Cnpj;
import com.drivelab.autocenter.domain.InternalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, InternalId> {

    Optional<Supplier> findByCnpj(Cnpj cnpj);

    Optional<Supplier> findByPublicId(SupplierPublicId publicId);
}
