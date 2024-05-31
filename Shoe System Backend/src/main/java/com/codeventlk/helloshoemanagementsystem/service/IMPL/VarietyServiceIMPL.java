package com.codeventlk.helloshoemanagementsystem.service.IMPL;

import com.codeventlk.helloshoemanagementsystem.conversion.ConversionData;
import com.codeventlk.helloshoemanagementsystem.dto.OccasionDTO;
import com.codeventlk.helloshoemanagementsystem.dto.VarietyDTO;
import com.codeventlk.helloshoemanagementsystem.exception.DuplicateException;
import com.codeventlk.helloshoemanagementsystem.exception.NotFoundException;
import com.codeventlk.helloshoemanagementsystem.repository.VarietyServiceDao;
import com.codeventlk.helloshoemanagementsystem.service.VarietyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VarietyServiceIMPL implements VarietyService {

    private final VarietyServiceDao varietyServiceDao;
    private final ConversionData conversionData;
    @Override
    public void saveVariety(VarietyDTO varietyDTO) {
        if (varietyServiceDao.existsById(varietyDTO.getVarietyCode())) throw new DuplicateException("Variety Id Duplicate");
        varietyServiceDao.save(conversionData.toVarietyEntity(varietyDTO));
    }

    @Override
    public List<VarietyDTO> getAllVariety() {
        return conversionData.convertToVarietyDTO(varietyServiceDao.findAll());
    }

    @Override
    public void deleteVariety(String id) {
        if (!varietyServiceDao.existsById(id)) throw new NotFoundException("Variety Not Found");
        varietyServiceDao.deleteById(id);
    }

    @Override
    public void updateVariety(String id, VarietyDTO varietyDTO) {
        if (!varietyServiceDao.existsById(id)) throw new NotFoundException("Variety Not Found");
        varietyServiceDao.save(conversionData.toVarietyEntity(varietyDTO));
    }
}
