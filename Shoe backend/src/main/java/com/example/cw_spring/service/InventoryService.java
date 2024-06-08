package com.example.cw_spring.service;


import com.example.cw_spring.dto.InventoryDTO;

import java.util.List;

public interface InventoryService {
    boolean saveInventory(InventoryDTO employeeDTO, String supplier_code);
    List<InventoryDTO> getAllInventory();
    boolean updateInventory(String id, InventoryDTO employeeDTO);
    boolean deleteInventory(String id);
    String generateNextId(String occupation, String gender);
    InventoryDTO selectInventory(String id);
    boolean updateImage(String item_code, String item_picture);
    List<String> getSizes(String item_code);
}
