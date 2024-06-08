package com.example.cw_spring.entity;

import com.example.cw_spring.enums.Gender;
import com.example.cw_spring.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "employee")
@Entity

public class EmployeeEntity extends BaseEntity{
    @Id
    private String employee_code;
    private String employee_name;
    @Column(columnDefinition = "LONGTEXT")
    private String employee_profile_pic;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String status;
    private String designation;
    @Enumerated(EnumType.STRING)
    private Role access_role;
    private Date dob;
    private Date date_of_join;
    private String attached_branch;
    private String address_line_01;
    private String address_line_02;
    private String address_line_03;
    private String address_line_04;
    private String address_line_05;
    private String contact_no;
    private String email;
    private String in_case_of_emergency;
    private String emergency_contact_no;
}
