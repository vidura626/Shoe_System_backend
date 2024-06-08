package com.example.cw_spring.controller;

import com.example.cw_spring.dto.SupplierDTO;
import com.example.cw_spring.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping("/health")
    public String healthTest() {
        return "Health check passed!";
    }

    @PostMapping("/save")
    public boolean saveSupplier(@RequestBody SupplierDTO supplierDTO) {
        supplierDTO.setSupplier_code(UUID.randomUUID().toString());
        return supplierService.saveSupplier(supplierDTO);
    }

    @PutMapping("/update")
    public boolean updateSupplier(@RequestBody SupplierDTO supplierDTO) {
        return supplierService.updateSupplier(supplierDTO.getSupplier_code(), supplierDTO);
    }

    @DeleteMapping("/{supplier_code}")
    public boolean deleteSupplier(@PathVariable ("supplier_code") String supplier_code) {
        return supplierService.deleteSupplier(supplier_code);
    }

    @GetMapping
    public List<SupplierDTO> getAllSupplier() {
        return supplierService.getAllSupplier();
    }
}
