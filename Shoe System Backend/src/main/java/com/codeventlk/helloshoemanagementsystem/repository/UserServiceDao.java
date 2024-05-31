package com.codeventlk.helloshoemanagementsystem.repository;

import com.codeventlk.helloshoemanagementsystem.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserServiceDao extends JpaRepository<UserEntity,String> {
    Optional<UserEntity> findByEmail(String email);
}
