package com.codeventlk.helloshoemanagementsystem.repository;

import com.codeventlk.helloshoemanagementsystem.entity.CustomerEntity;
import com.codeventlk.helloshoemanagementsystem.entity.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeServiceDao extends JpaRepository<SizeEntity,String> {
    SizeEntity findFirstByOrderBySizeCodeDesc();
}
