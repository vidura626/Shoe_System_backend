package com.codeventlk.helloshoemanagementsystem.service;

import com.codeventlk.helloshoemanagementsystem.dto.GenderDTO;
import com.codeventlk.helloshoemanagementsystem.dto.SizeDTO;

import java.util.List;

public interface SizeService {
    void saveSize(SizeDTO sizeDTO);

    List<SizeDTO> getAllSizes();

    void deleteSize(String id);

    void updateSize(String id,SizeDTO sizeDTO);

    String getSizeId();
}
