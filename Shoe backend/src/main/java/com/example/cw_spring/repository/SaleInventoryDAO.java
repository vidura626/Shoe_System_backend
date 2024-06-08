package com.example.cw_spring.repository;

import com.example.cw_spring.entity.SaleInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleInventoryDAO extends JpaRepository<SaleInventoryEntity, String> {
}
