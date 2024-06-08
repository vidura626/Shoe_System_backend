package com.example.cw_spring.controller;

import com.example.cw_spring.dto.InventoryDTO;
import com.example.cw_spring.enums.InventoryGender;
import com.example.cw_spring.service.InventoryService;
import com.example.cw_spring.util.UtilMatters;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/inventory")
@CrossOrigin(origins = "*")

public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/health")
    public String healthTest() {
        return "Health check passed!";
    }

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean saveInventory(
        @RequestPart("item_desc") String item_desc,
        @RequestPart("item_picture") String item_picture,
        @RequestPart("category") String category,
        @RequestPart("status") String status,
        @RequestPart("gender") String gender,
        @RequestPart("occupation") String occupation,
        @RequestPart("supplier_code") String supplier_code
    ) throws Exception {
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setItem_code(inventoryService.generateNextId(occupation, gender));
        inventoryDTO.setItem_desc(item_desc);
        inventoryDTO.setItem_picture(item_picture);
        inventoryDTO.setCategory(category);
        inventoryDTO.setGender(InventoryGender.valueOf(gender));
        inventoryDTO.setOccupation(occupation);

        return inventoryService.saveInventory(inventoryDTO, supplier_code);
    }

    @GetMapping
    public List<InventoryDTO> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public boolean updateInventory(
            @RequestPart("item_code") String item_code,
            @RequestPart("item_desc") String item_desc,
            @RequestPart("item_qty") String item_qty,
            @RequestPart("item_picture") String item_picture,
            @RequestPart("category") String category,
            @RequestPart("size") String size,
            @RequestPart("unit_price_sale") String unit_price_sale,
            @RequestPart("unit_price_buy") String unit_price_buy,
            @RequestPart("expected_profit") String expected_profit,
            @RequestPart("profit_margin") String profit_margin,
            @RequestPart("status") String status
    ) throws Exception {
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setItem_code(item_code);
        inventoryDTO.setItem_desc(item_desc);
        inventoryDTO.setItem_picture(UtilMatters.convertBase64(item_picture));
        inventoryDTO.setCategory(category);
        inventoryDTO.setItem_qty(Integer.parseInt(item_qty));
        inventoryDTO.setUnit_price_sale(Double.parseDouble(unit_price_sale));
        inventoryDTO.setUnit_price_buy(Double.parseDouble(unit_price_buy));
        inventoryDTO.setExpected_profit(Double.parseDouble(expected_profit));
        inventoryDTO.setProfit_margin(Double.parseDouble(profit_margin));

        return inventoryService.updateInventory(item_code, inventoryDTO);
    }
    @DeleteMapping("/delete")
    public boolean deleteInventory(String item_code) {
        return inventoryService.deleteInventory(item_code);
    }

    @GetMapping("/selectInventory")
    public InventoryDTO selectInventory(String item_code) {
        return inventoryService.selectInventory(item_code);
    }

    @PostMapping("/updateImage")
    public boolean updateImage(
            @RequestPart("item_code") String item_code,
            @RequestPart("item_picture") String item_picture
    ) throws Exception {
        return inventoryService.updateImage(item_code, UtilMatters.convertBase64(item_picture));
    }
}
