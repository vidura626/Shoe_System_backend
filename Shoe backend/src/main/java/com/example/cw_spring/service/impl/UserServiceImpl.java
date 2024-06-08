package com.example.cw_spring.service.impl;

import com.example.cw_spring.dto.UserDTO;
import com.example.cw_spring.repository.UserDAO;
import com.example.cw_spring.service.UserService;
import com.example.cw_spring.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final Mapping map;
    @Override
    public UserDetailsService userDetailsService() {
        return username ->
                userDAO.findByEmail(username)
                        .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        map.toUserDTO(userDAO.save(map.toUser(userDTO)));
    }

}
