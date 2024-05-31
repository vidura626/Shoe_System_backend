package com.codeventlk.helloshoemanagementsystem.service;

import com.codeventlk.helloshoemanagementsystem.dto.GenderDTO;

import java.util.List;

public interface GenderService {
    void saveGender(GenderDTO genderDTO);

    List<GenderDTO> getAllGenders();

    void deleteGender(String id);

    void updateGender(String id, GenderDTO genderDTO);

}
