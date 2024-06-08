package com.example.cw_spring.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUsername(String username);

    String generateToken(UserDetails userDetails);
    boolean istokenValid(String token, UserDetails userDetails);
}
