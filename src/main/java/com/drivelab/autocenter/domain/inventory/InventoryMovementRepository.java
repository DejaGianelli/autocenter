package com.drivelab.autocenter.domain.inventory;

import com.drivelab.autocenter.domain.InternalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, InternalId> {

}
