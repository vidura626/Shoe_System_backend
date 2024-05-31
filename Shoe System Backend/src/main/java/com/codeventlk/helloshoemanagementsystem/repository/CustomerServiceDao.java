package com.codeventlk.helloshoemanagementsystem.repository;

import com.codeventlk.helloshoemanagementsystem.dto.CustomerDTO;
import com.codeventlk.helloshoemanagementsystem.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerServiceDao extends JpaRepository <CustomerEntity,String> {
    CustomerEntity findFirstByOrderByCustomerIdDesc();
}
