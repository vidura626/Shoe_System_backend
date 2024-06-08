package com.example.cw_spring.service.impl;

import com.example.cw_spring.dto.InventoryDTO;
import com.example.cw_spring.entity.InventoryEntity;
import com.example.cw_spring.entity.SupplierEntity;
import com.example.cw_spring.entity.SupplierInventoryEntity;
import com.example.cw_spring.repository.InventoryDAO;
import com.example.cw_spring.repository.SizeDAO;
import com.example.cw_spring.repository.SupplierDAO;
import com.example.cw_spring.service.InventoryService;
import com.example.cw_spring.service.SupplierInventoryService;
import com.example.cw_spring.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor

public class InventoryServiceImpl implements InventoryService {
    private final InventoryDAO inventoryDAO;
    private final SupplierDAO supplierDAO;
    private SupplierInventoryService supplierInventoryService;
    private final Mapping mapping;
    private final SizeDAO sizeDAO;
    @Override
    public boolean saveInventory(InventoryDTO inventoryDTO, String supplier_code) {
        InventoryEntity isSaved = inventoryDAO.save(mapping.toInventory(inventoryDTO));

        Optional<SupplierEntity> supplier = supplierDAO.findById(supplier_code);
        if(supplier.isPresent()){
            SupplierInventoryEntity supplierInventoryEntity = new SupplierInventoryEntity();
            supplierInventoryEntity.setSupplier_inventory_id(UUID.randomUUID().toString());
            supplierInventoryEntity.setInventory_Entity(isSaved);
            supplierInventoryEntity.setSupplierEntity(supplier.get());
            supplierInventoryService.save(supplierInventoryEntity);
            return true;
        }
        return false;
    }

    @Override
    public List<InventoryDTO> getAllInventory() {
        return mapping.toInventoryDTOList(inventoryDAO.findAll());
    }

    @Override
    public boolean deleteInventory(String id) {
        Optional<InventoryEntity> inventory = inventoryDAO.findById(id);
        if (inventory.isPresent()) {
            inventoryDAO.delete(inventory.get());
            return true;
        }else {
            throw new RuntimeException("Inventory not found");
        }
    }

    @Override
    public boolean updateInventory(String id, InventoryDTO inventoryDTO) {
        Optional<InventoryEntity> inventory = inventoryDAO.findById(id);
        if (inventory.isPresent()) {
            inventory.get().setItem_desc(inventoryDTO.getItem_desc());
            inventory.get().setItem_picture(inventoryDTO.getItem_picture());
            inventory.get().setCategory(inventoryDTO.getCategory());
            return true;
        }else {
            throw new RuntimeException("Inventory not found");
        }
    }

    @Override
    public String generateNextId(String occupation, String gender) {
        String occ="";
        switch (occupation) {
            case "Casual": occ = "CS";break;
            case "Formal": occ = "FS";break;
            case "Sport": occ = "SS";break;
            case "Industrial": occ = "IS";break;
        }

        switch (gender) {
            case "MAN": occ = occ + "M";break;
            case "WOMEN": occ = occ + "W";break;
        }

        String lastId = inventoryDAO.findLastId(occ);
        if(lastId == null){
            return occ + "0001";
        }else{
            int id = Integer.parseInt(lastId.substring(3)) + 1;
            return occ + String.format("%04d", id);
        }
    }

    @Override
    public InventoryDTO selectInventory(String id) {
        Optional<InventoryEntity> inventory = inventoryDAO.findById(id);
        if (inventory.isPresent()) {
            return mapping.toInventoryDTO(inventory.get());
        }else {
            throw new RuntimeException("Inventory not found");
        }
    }

    @Override
    public boolean updateImage(String item_code, String item_picture) {
        Optional<InventoryEntity> inventory = inventoryDAO.findById(item_code);
        if (inventory.isPresent()) {
            inventory.get().setItem_picture(item_picture);
            return true;
        }else {
            throw new RuntimeException("Inventory not found");
        }
    }

    @Override
    public List<String> getSizes(String item_code) {
        return inventoryDAO.getSizes(item_code);
    }
}
