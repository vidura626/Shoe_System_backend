package com.example.cw_spring.dto;

import com.example.cw_spring.enums.Gender;
import com.example.cw_spring.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CustomerDTO implements SuperDTO {
    private String customer_code;
    private String customer_name;
    private Gender gender;
    private String join_date;
    private Level level;
    private int total_points;
    private String  dob;
    private String address_line_01;
    private String address_line_02;
    private String address_line_03;
    private String address_line_04;
    private String address_line_05;
    private String contact_no;
    private String email;
    private String purchase_date_time;
}
