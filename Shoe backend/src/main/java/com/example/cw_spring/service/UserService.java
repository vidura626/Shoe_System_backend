package com.example.cw_spring.service;

import com.example.cw_spring.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();
    void saveUser(UserDTO userDTO);
}
