package com.example.cw_spring.service;

import com.example.cw_spring.dto.SupplierDTO;

import java.util.List;

public interface SupplierService {
    boolean saveSupplier(SupplierDTO supplierDTO);
    List<SupplierDTO> getAllSupplier();
    boolean deleteSupplier(String supplier_code);
    boolean updateSupplier(String supplier_code, SupplierDTO supplierDTO);
    SupplierDTO selectSupplierById(String email);
    String generateNextID();
    List<String> getAllSupplierIds();
}
