package com.example.cw_spring.repository;

import com.example.cw_spring.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO extends JpaRepository<CustomerEntity, String> {
    Optional<CustomerEntity> findByEmail(String email);

    @Query(value = "SELECT customer_code FROM customer", nativeQuery = true)
    List<String> getAllCustomerIds();

    @Query(value = "SELECT customer_code FROM customer ORDER BY customer_code DESC LIMIT 1", nativeQuery = true)
    String findLastId();
}
