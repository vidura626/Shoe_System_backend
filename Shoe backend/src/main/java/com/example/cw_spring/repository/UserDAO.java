package com.example.cw_spring.repository;

import com.example.cw_spring.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserDAO extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
}
