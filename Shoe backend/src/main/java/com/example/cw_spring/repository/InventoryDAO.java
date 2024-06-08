package com.example.cw_spring.repository;

import com.example.cw_spring.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryDAO extends JpaRepository<InventoryEntity, String> {

    @Query(value = "SELECT item_code FROM inventory_entity WHERE item_code LIKE CONCAT(:occ, '%') ORDER BY item_code DESC LIMIT 1", nativeQuery = true)
    String findLastId(String occ);

    @Query(value = "SELECT item_code FROM inventory_entity", nativeQuery = true)
    List<String> getItemIds();

    @Query(value = "SELECT size FROM size WHERE item_code = :item_code", nativeQuery = true)
    List<String> getSizes(@Param("item_code") String item_code);
}
