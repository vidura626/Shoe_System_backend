package com.codeventlk.helloshoemanagementsystem.repository;

import com.codeventlk.helloshoemanagementsystem.entity.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderServiceDao extends JpaRepository<GenderEntity,String> {
}
