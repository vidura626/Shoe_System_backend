package com.example.cw_spring.entity;

import com.example.cw_spring.enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "supplier")
@Entity
public class SupplierEntity extends BaseEntity{
    @Id
    private String supplier_code;
    private String supplier_name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String address_line_01;
    private String address_line_02;
    private String address_line_03;
    private String address_line_04;
    private String address_line_05;
    private String address_line_06;
    private String contact_no_01;
    private String contact_no_02;
    private String email;
}
