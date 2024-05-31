package com.codeventlk.helloshoemanagementsystem.service;

import com.codeventlk.helloshoemanagementsystem.dto.ItemDTO;

public interface InventoryService {
    void saveItem(ItemDTO itemDTO);

    ItemDTO getItem(String id);

    void deleteItem(String id);

    void updateItem(String id, String itemDesc, String pic);
}
