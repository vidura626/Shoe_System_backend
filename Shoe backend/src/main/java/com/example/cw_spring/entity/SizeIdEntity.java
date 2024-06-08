package com.example.cw_spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SizeIdEntity extends BaseEntity{
    private int size;
    private InventoryEntity inventory;
}
