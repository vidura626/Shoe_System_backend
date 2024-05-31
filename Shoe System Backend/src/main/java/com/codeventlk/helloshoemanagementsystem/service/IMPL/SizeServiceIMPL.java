package com.codeventlk.helloshoemanagementsystem.service.IMPL;

import com.codeventlk.helloshoemanagementsystem.conversion.ConversionData;
import com.codeventlk.helloshoemanagementsystem.dto.SizeDTO;
import com.codeventlk.helloshoemanagementsystem.entity.CustomerEntity;
import com.codeventlk.helloshoemanagementsystem.entity.SizeEntity;
import com.codeventlk.helloshoemanagementsystem.exception.DuplicateException;
import com.codeventlk.helloshoemanagementsystem.exception.NotFoundException;
import com.codeventlk.helloshoemanagementsystem.repository.GenderServiceDao;
import com.codeventlk.helloshoemanagementsystem.repository.SizeServiceDao;
import com.codeventlk.helloshoemanagementsystem.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class SizeServiceIMPL implements SizeService {

    private final SizeServiceDao sizeServiceDao;
    private final ConversionData conversionData;
    @Override
    public void saveSize(SizeDTO sizeDTO) {
        if (sizeServiceDao.existsById(sizeDTO.getSizeCode())) throw new DuplicateException("Size Id Duplicate");
        sizeServiceDao.save(conversionData.toSizeEntity(sizeDTO));
    }

    @Override
    public List<SizeDTO> getAllSizes() {
        return conversionData.convertToSizeDTOs(sizeServiceDao.findAll());
    }

    @Override
    public void deleteSize(String id) {
        if (!sizeServiceDao.existsById(id)) throw new NotFoundException("Size Not Found");
        sizeServiceDao.deleteById(id);
    }

    @Override
    public void updateSize(String id, SizeDTO sizeDTO) {
        if (!sizeServiceDao.existsById(id)) throw new NotFoundException("Size Not Found");
        sizeServiceDao.save(conversionData.toSizeEntity(sizeDTO));
    }

    @Override
    public String getSizeId() {
        return getNextSizeId();
    }

    private String getNextSizeId() {
        SizeEntity firstByOrderBySizeCodeDesc = sizeServiceDao.findFirstByOrderBySizeCodeDesc();
        return (firstByOrderBySizeCodeDesc != null)
                ? String.format("Size-%03d",
                Integer.parseInt(firstByOrderBySizeCodeDesc.getSizeCode().
                        replace("Size-", "")) + 1)
                : "Size-001";
    }
}
