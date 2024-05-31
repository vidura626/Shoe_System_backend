package com.codeventlk.helloshoemanagementsystem.repository;

import com.codeventlk.helloshoemanagementsystem.dto.SupplierDTO;
import com.codeventlk.helloshoemanagementsystem.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierServiceDao extends JpaRepository<SupplierEntity,String> {
    SupplierEntity findFirstByOrderBySupplierCode();
}
