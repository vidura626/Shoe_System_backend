package com.example.cw_spring.dto;

import com.example.cw_spring.enums.InventoryGender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class InventoryDTO implements SuperDTO{
    private String item_code;
    private String item_desc;
    private int item_qty;
    private String item_picture;
    private String category;
    private double unit_price_sale;
    private double unit_price_buy;
    private double expected_profit;
    private double profit_margin;
    private InventoryGender gender;
    private String occupation;
    private String supplier_code;
}
