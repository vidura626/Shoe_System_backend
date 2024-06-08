package com.example.cw_spring.entity;

import com.example.cw_spring.enums.InventoryGender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "inventory")
@Entity
public class InventoryEntity extends BaseEntity{
    @Id
    private String item_code;
    private String item_desc;
    @Column(columnDefinition = "LONGTEXT")
    private String item_picture;
    private String category;
    @Enumerated(EnumType.STRING)
    private InventoryGender gender;
    private String occupation;

    /*@JsonIgnore
    @OneToMany(mappedBy = "inventory")
    private Set<SaleInventoryEntity> saleInventoryEntity = new HashSet<>();*/

    /*@JsonIgnore
    @OneToMany(mappedBy = "inventory")
    private Set<SupplierInventoryEntity> supplierInventoryEntity = new HashSet<>();*/

    @JsonIgnore
    @OneToMany(mappedBy = "inventory")
    private List<SizeEntity> sizes = new ArrayList<>();
}
