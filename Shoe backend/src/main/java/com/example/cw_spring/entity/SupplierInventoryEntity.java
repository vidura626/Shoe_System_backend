package com.example.cw_spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "supplier_inventory")
@Entity

public class SupplierInventoryEntity {
    @Id
    private String supplier_inventory_id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "item_code")
    private InventoryEntity inventory_Entity;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplierEntity;
}
