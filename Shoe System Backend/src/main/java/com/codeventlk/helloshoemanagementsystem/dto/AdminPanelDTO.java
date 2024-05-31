package com.codeventlk.helloshoemanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class AdminPanelDTO {
    @NotNull(message = "Total Sale cannot be null.")
    @PositiveOrZero(message = "Total Sales cannot be negative.")
    private Double totalSales;
    @NotNull(message = "Total Profit cannot be null.")
    @PositiveOrZero(message = "Total Profit cannot be negative.")
    private Double totalProfit;
    @NotBlank(message = "Most Sale Item cannot be null.")
    private String mostSaleItem;
    @NotBlank(message = "Item picture cannot be null.")
    private String itemPic;
    @NotBlank(message = "Sale Item Qty cannot be null.")
    @PositiveOrZero(message = "Sale Item Qty cannot be negative.")
    private int saleItemQty;
}
