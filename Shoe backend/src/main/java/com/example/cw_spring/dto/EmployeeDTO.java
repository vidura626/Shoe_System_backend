package com.example.cw_spring.dto;

import com.example.cw_spring.enums.Gender;
import com.example.cw_spring.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class EmployeeDTO implements SuperDTO {

    private String employee_code;
    private String employee_name;
    private String employee_profile_pic;
    private Gender gender;
    private String status;
    private String designation;
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
    private String password;
}
