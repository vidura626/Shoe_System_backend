package com.example.cw_spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "sale")
public class SaleEntity extends BaseEntity{
    @Id
    private String order_id;
    private String customer_name;
    private double total_price;
    private Date purchase_date;
    private String payment_method;
    private double added_points;
    private String cashier_name;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "sale_Entity", cascade = CascadeType.ALL)
    private Set<SaleInventoryEntity> saleInventoryEntity = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "customer_code")
    private CustomerEntity customerEntity;
}
