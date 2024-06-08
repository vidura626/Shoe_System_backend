package com.example.cw_spring.controller;

import com.example.cw_spring.ProjectionInterface.MostSolidItemProjection;
import com.example.cw_spring.dto.PlaceOrderRequestDTO;
import com.example.cw_spring.entity.InventoryEntity;
import com.example.cw_spring.service.InventoryService;
import com.example.cw_spring.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sale")
public class SaleController {
    @Autowired
    private  SaleService saleService;
    private InventoryService inventoryService;

    @GetMapping("/health")
    public String healthTest() {
        return "Health check passed!";
    }
    @GetMapping("/getItemIds")
    public List<String> getItemIds() {
        return saleService.getItemIds();
    }

    @GetMapping("getItem/{item_code}")
    public InventoryEntity getItem(@PathVariable String item_code) {
        return saleService.getItem(item_code);
    }

    @GetMapping("getItemSize/{item_code}")
    public List<String> getSize(@PathVariable String item_code) {
        return inventoryService.getSizes(item_code);
    }

    @PostMapping("/placeOrder")
    public boolean placeOrder(@RequestBody PlaceOrderRequestDTO placeOrderRequestDTO) {
        System.out.println(placeOrderRequestDTO);
        return saleService.placeOrder(placeOrderRequestDTO);
    }

    @GetMapping("/getTotalSales/{date}")
    public Double getTotalSales(@PathVariable String date) {
        return saleService.getTotalSales(date);
    }

    @GetMapping("/getTotalProfit/{date}")
    public Double getTotalProfit(@PathVariable String date) {
        return saleService.getTotalProfit(date);
    }

    @GetMapping("/getMostSolidItem/{date}")
    public MostSolidItemProjection getMostSolidItem(@PathVariable String date) {
        return saleService.getMostSolidItem(date);
    }
}
