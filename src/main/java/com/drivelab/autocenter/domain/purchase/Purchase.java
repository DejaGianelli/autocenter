package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.DomainEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "purchases")
public class Purchase extends DomainEntity {
}
