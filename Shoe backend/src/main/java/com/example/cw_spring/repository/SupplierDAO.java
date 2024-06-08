package com.example.cw_spring.repository;

import com.example.cw_spring.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SupplierDAO extends JpaRepository<SupplierEntity, String> {
    Optional<SupplierEntity> findByEmail(String email);

    @Query(value = "SELECT supplier_code FROM supplier ORDER BY supplier_id DESC LIMIT 1", nativeQuery = true)
    String findLastId();

    @Query(value = "SELECT supplier_code FROM supplier", nativeQuery = true)
    List<String> getSupplierIds();
}
