package com.codeventlk.helloshoemanagementsystem.repository;

import com.codeventlk.helloshoemanagementsystem.entity.VarietyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VarietyServiceDao extends JpaRepository<VarietyEntity,String> {
}
