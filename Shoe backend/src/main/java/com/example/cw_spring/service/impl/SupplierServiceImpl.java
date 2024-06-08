package com.example.cw_spring.service.impl;

import com.example.cw_spring.dto.SupplierDTO;
import com.example.cw_spring.entity.SupplierEntity;
import com.example.cw_spring.repository.SupplierDAO;
import com.example.cw_spring.service.SupplierService;
import com.example.cw_spring.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor

public class SupplierServiceImpl implements SupplierService {
    private final SupplierDAO supplierDAO;
    private final Mapping mapping;
    @Override
    public boolean saveSupplier(SupplierDTO supplierDTO) {
        SupplierEntity supplier = supplierDAO.save(mapping.toSupplier(supplierDTO));
        return supplier != null;
    }

    @Override
    public boolean updateSupplier(String supplier_code, SupplierDTO supplierDTO) {
        Optional<SupplierEntity> supplier = supplierDAO.findById(supplier_code);
        if (supplier.isPresent()) {
            SupplierEntity supplierEntity = supplier.get();
            supplierEntity.setSupplier_name(supplierDTO.getSupplier_name());
            supplierEntity.setCategory(supplierDTO.getCategory());
            supplierEntity.setAddress_line_01(supplierDTO.getAddress_line_01());
            supplierEntity.setAddress_line_02(supplierDTO.getAddress_line_02());
            supplierEntity.setAddress_line_03(supplierDTO.getAddress_line_03());
            supplierEntity.setAddress_line_04(supplierDTO.getAddress_line_04());
            supplierEntity.setAddress_line_05(supplierDTO.getAddress_line_05());
            supplierEntity.setAddress_line_06(supplierDTO.getAddress_line_06());
            supplierEntity.setContact_no_01(supplierDTO.getContact_no_01());
            supplierEntity.setContact_no_02(supplierDTO.getContact_no_02());
            supplierEntity.setEmail(supplierDTO.getEmail());
            supplierDAO.save(supplierEntity);
            return true;
        }
        return false;
    }

    @Override
    public SupplierDTO selectSupplierById(String email) {
        return null;
    }

    @Override
    public String generateNextID() {
        return null;
    }

    @Override
    public List<String> getAllSupplierIds() {
        return null;
    }

    @Override
    public boolean deleteSupplier(String supplier_code) {
        supplierDAO.deleteById(supplier_code);
        return true;
    }

    @Override
    public List<SupplierDTO> getAllSupplier() {
        return mapping.toSupplierDTOList(supplierDAO.findAll());
    }
}
