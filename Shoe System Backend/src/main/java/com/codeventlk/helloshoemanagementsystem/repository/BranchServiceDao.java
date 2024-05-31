package com.codeventlk.helloshoemanagementsystem.repository;

import com.codeventlk.helloshoemanagementsystem.entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchServiceDao extends JpaRepository<BranchEntity,String> {
}
