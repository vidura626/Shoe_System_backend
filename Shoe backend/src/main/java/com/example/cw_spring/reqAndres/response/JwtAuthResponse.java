package com.example.cw_spring.reqAndres.response;

import com.example.cw_spring.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class JwtAuthResponse {
    private String token;
    private Role role;
}
