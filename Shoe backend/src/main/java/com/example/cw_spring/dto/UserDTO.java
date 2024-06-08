package com.example.cw_spring.dto;

import com.example.cw_spring.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class UserDTO {

    private String userId;
    private String email;
    private String password;
    private Role role;
    private String otp;
}
