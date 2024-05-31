package com.codeventlk.helloshoemanagementsystem.service;

import com.codeventlk.helloshoemanagementsystem.dto.OccasionDTO;
import com.codeventlk.helloshoemanagementsystem.dto.VarietyDTO;

import java.util.List;

public interface VarietyService {
    void saveVariety(VarietyDTO varietyDTO);

    List<VarietyDTO> getAllVariety();

    void deleteVariety(String id);

    void updateVariety(String id,VarietyDTO varietyDTO);
}
