package com.example.cw_spring.repository;

import com.example.cw_spring.entity.SupplierInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierInventoryDAO extends JpaRepository<SupplierInventoryEntity, String> {
}
