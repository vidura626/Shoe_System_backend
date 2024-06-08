package com.example.cw_spring.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "size")
@Entity
@IdClass(SizeIdEntity.class)
public class SizeEntity extends BaseEntity{
    @Id
    private int size;
    @JsonManagedReference
    @ManyToOne
    @Id
    private InventoryEntity inventory;
    private int quantity;
    private double unit_price_sale;
    private double unit_price_buy;
    private double expected_profit;
    private String status;
    private double profit_margin;

}
