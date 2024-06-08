package com.example.cw_spring.service;

import com.example.cw_spring.dto.EmployeeDTO;
import com.example.cw_spring.reqAndres.response.JwtAuthResponse;
import com.example.cw_spring.reqAndres.secure.SignIn;
import com.example.cw_spring.reqAndres.secure.SignUp;

public interface AuthenticationService {
    JwtAuthResponse signIn(SignIn signIn);

    Boolean sendOTP(String email);
    Boolean confirmOTP(String email);
    JwtAuthResponse signUp(SignUp signUp, EmployeeDTO employeeDTO);
    JwtAuthResponse signUp();
    JwtAuthResponse refreshToken(String refreshToken);
}
