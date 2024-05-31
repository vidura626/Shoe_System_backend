package com.codeventlk.helloshoemanagementsystem.service;


import com.codeventlk.helloshoemanagementsystem.secureAndResponse.response.JwtAuthResponse;
import com.codeventlk.helloshoemanagementsystem.secureAndResponse.secure.SignIn;
import com.codeventlk.helloshoemanagementsystem.secureAndResponse.secure.SignUp;

public interface AuthenticationService {
    JwtAuthResponse signUp(SignUp signup);
    JwtAuthResponse signIn(SignIn signIn);
    JwtAuthResponse refreshToken(String refreshToken);
}
