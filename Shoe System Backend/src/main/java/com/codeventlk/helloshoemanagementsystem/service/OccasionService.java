package com.codeventlk.helloshoemanagementsystem.service;

import com.codeventlk.helloshoemanagementsystem.dto.GenderDTO;
import com.codeventlk.helloshoemanagementsystem.dto.OccasionDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OccasionService {
    void saveOccasion(OccasionDTO occasionDTO);

    List<OccasionDTO> getAllOccasion();

    void deleteOccasion(String id);

    void updateOccasion(String id, OccasionDTO occasionDTO);
}
