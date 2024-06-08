package com.example.cw_spring.entity;

import com.example.cw_spring.enums.Gender;
import com.example.cw_spring.enums.Level;
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
@Table(name = "customer")
@Entity
public class CustomerEntity extends BaseEntity{
    @Id
    private String customer_code;
    private String customer_name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String join_date;
    @Enumerated(EnumType.STRING)
    private Level level;
    private int total_points;
    private String  dob;
    private String address_line_01;
    private String address_line_02;
    private String address_line_03;
    private String address_line_04;
    private String address_line_05;
    private String contact_no;
    @Column(unique = true)
    private String email;
    private String  purchase_date_time;

@OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL)
    private List<SaleEntity> sales = new ArrayList<>();

}
