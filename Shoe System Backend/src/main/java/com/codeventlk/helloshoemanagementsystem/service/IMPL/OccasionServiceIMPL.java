package com.codeventlk.helloshoemanagementsystem.service.IMPL;

import com.codeventlk.helloshoemanagementsystem.conversion.ConversionData;
import com.codeventlk.helloshoemanagementsystem.dto.OccasionDTO;
import com.codeventlk.helloshoemanagementsystem.entity.OccasionEntity;
import com.codeventlk.helloshoemanagementsystem.exception.DuplicateException;
import com.codeventlk.helloshoemanagementsystem.exception.NotFoundException;
import com.codeventlk.helloshoemanagementsystem.repository.OccasionServiceDao;
import com.codeventlk.helloshoemanagementsystem.service.OccasionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OccasionServiceIMPL implements OccasionService {

    private final OccasionServiceDao occasionServiceDao;
    private final ConversionData conversionData;


    @Override
    public void saveOccasion(OccasionDTO occasionDTO) {
        if (occasionServiceDao.existsById(occasionDTO.getOccasionCode())) throw new DuplicateException("Occasion Id Duplicate");
        occasionServiceDao.save(conversionData.toOccasionEntity(occasionDTO));
    }

    @Override
    public List<OccasionDTO> getAllOccasion() {
        return conversionData.convertToOccasionDTO(occasionServiceDao.findAll());
    }

    @Override
    public void deleteOccasion(String id) {
        if (!occasionServiceDao.existsById(id)) throw new NotFoundException("Occasion Not Found");
        occasionServiceDao.deleteById(id);
    }

    @Override
    public void updateOccasion(String id, OccasionDTO occasionDTO) {
        if (!occasionServiceDao.existsById(id)) throw new NotFoundException("Occasion Not Found");
        occasionServiceDao.save(conversionData.toOccasionEntity(occasionDTO));
    }
}
