package com.codeventlk.helloshoemanagementsystem.service.IMPL;

import com.codeventlk.helloshoemanagementsystem.Util.UtilMatters;
import com.codeventlk.helloshoemanagementsystem.conversion.ConversionData;
import com.codeventlk.helloshoemanagementsystem.dto.BranchDTO;
import com.codeventlk.helloshoemanagementsystem.exception.InvalidException;
import com.codeventlk.helloshoemanagementsystem.exception.NotFoundException;
import com.codeventlk.helloshoemanagementsystem.repository.BranchServiceDao;
import com.codeventlk.helloshoemanagementsystem.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BranchServiceIMPL implements BranchService {

    final private ConversionData conversionData;

    final private BranchServiceDao branchServiceDao;
    @Override
    public void saveBranch(BranchDTO branchDTO){
        if (!branchDTO.getProductCode().equals(UtilMatters.productActivationCode())) throw new InvalidException("Invalid Product Code");
        branchDTO.setBranchId(UtilMatters.generateId());
        branchServiceDao.save(conversionData.toBranchEntity(branchDTO));
    }
}
