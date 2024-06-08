package com.example.cw_spring.repository;

import com.example.cw_spring.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeDAO extends JpaRepository<EmployeeEntity, String> {

    Optional<EmployeeEntity> findByEmail(String email);
}
