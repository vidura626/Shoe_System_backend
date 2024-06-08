package com.example.cw_spring.dto;

import com.example.cw_spring.entity.CustomerEntity;
import com.example.cw_spring.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SaleDTO {
    private String order_id;
    private String customer_name;
    private double total_price;
    private Date purchase_date;
    private String payment_method;
    private double added_points;
    private String cashier_name;
    private UserEntity userEntity;
    private CustomerEntity customerEntity;
}
