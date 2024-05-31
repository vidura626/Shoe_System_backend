package com.codeventlk.helloshoemanagementsystem.repository;

import com.codeventlk.helloshoemanagementsystem.dto.OccasionDTO;
import com.codeventlk.helloshoemanagementsystem.entity.OccasionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OccasionServiceDao extends JpaRepository<OccasionEntity,String> {
}
