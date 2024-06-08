package com.example.cw_spring.service;

import com.example.cw_spring.ProjectionInterface.MostSolidItemProjection;
import com.example.cw_spring.dto.PlaceOrderRequestDTO;
import com.example.cw_spring.entity.InventoryEntity;

import java.util.List;

public interface SaleService {
    List<String> getItemIds();
    InventoryEntity getItem(String item_code);
    boolean placeOrder(PlaceOrderRequestDTO placeOrderRequestDTO);
    Double getTotalSales(String date);
    Double getTotalProfit(String date);
    MostSolidItemProjection getMostSolidItem(String date);
}
