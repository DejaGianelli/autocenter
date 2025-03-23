package com.drivelab.autocenter.domain.inventory;

import com.drivelab.autocenter.domain.InternalId;
import com.drivelab.autocenter.domain.product.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface InventoryRepository extends JpaRepository<Inventory, InternalId> {

    Optional<Inventory> findByProduct(Product product);

    @Query("""
            SELECT inv
            FROM Inventory inv
            JOIN FETCH inv.product
            WHERE inv.product = :product
            """)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Inventory> findByProductForUpdate(Product product);
}
