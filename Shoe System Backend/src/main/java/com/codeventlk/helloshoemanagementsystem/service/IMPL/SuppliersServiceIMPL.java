package com.codeventlk.helloshoemanagementsystem.service.IMPL;

import com.codeventlk.helloshoemanagementsystem.conversion.ConversionData;
import com.codeventlk.helloshoemanagementsystem.dto.SupplierDTO;
import com.codeventlk.helloshoemanagementsystem.entity.SupplierEntity;
import com.codeventlk.helloshoemanagementsystem.exception.NotFoundException;
import com.codeventlk.helloshoemanagementsystem.repository.SupplierServiceDao;
import com.codeventlk.helloshoemanagementsystem.service.SupplierService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class SuppliersServiceIMPL implements SupplierService {

    final private ConversionData conversionData;

    final private SupplierServiceDao supplierServiceDao;
    @Override
    public void saveSupplier(SupplierDTO supplierDTO) {
        supplierDTO.setSupplierCode(getNextSupplierId());
        SupplierEntity supplierEntity = conversionData.convertToSupplierEntity(Optional.of(supplierDTO));
        supplierServiceDao.save(supplierEntity);
    }

    @Override
    public SupplierDTO getSupplier(String id) {
        if (!supplierServiceDao.existsById(id)) throw new NotFoundException("Supplier Not Found");
        return conversionData.convertToSupplierDTO(supplierServiceDao.findById(id));
    }

    @Override
    public List<SupplierDTO> getAllSuppliers() {
        return conversionData.getSupplierDTOList(supplierServiceDao.findAll());
    }

    @Override
    public void deleteSupplier(String id) {
        if (!supplierServiceDao.existsById(id)) throw new NotFoundException("Supplier Not Found");
        supplierServiceDao.deleteById(id);
    }

    @Override
    public void updateSupplier(String id, SupplierDTO supplierDTO) {
        Optional<SupplierEntity> supplierEntityOptional = supplierServiceDao.findById(id);
        if (supplierEntityOptional.isEmpty()) {
            throw new NotFoundException("Supplier Not Found");
        }

        SupplierEntity supplierEntity = supplierEntityOptional.get();

        supplierEntity.setSupplierName(supplierDTO.getSupplierName());
        supplierEntity.setCategory(supplierDTO.getCategory());
        supplierEntity.setAddress1(supplierDTO.getAddress1());
        supplierEntity.setAddress2(supplierDTO.getAddress2());
        supplierEntity.setAddress3(supplierDTO.getAddress3());
        supplierEntity.setAddress4(supplierDTO.getAddress4());
        supplierEntity.setPostalCode(supplierDTO.getPostalCode());
        supplierEntity.setCountry(supplierDTO.getCountry());
        supplierEntity.setContactNo1(supplierDTO.getContactNo1());
        supplierEntity.setContactNo2(supplierDTO.getContactNo2());
        supplierEntity.setEmail(supplierDTO.getEmail());
    }

    @Override
    public String getSupplierId() {
        return getNextSupplierId();
    }

    private String getNextSupplierId() {
        SupplierEntity firstByOrderBySupplierCode = supplierServiceDao.findFirstByOrderBySupplierCode();
        return (firstByOrderBySupplierCode != null)
                ? String.format("Sup-%03d",
                Integer.parseInt(firstByOrderBySupplierCode.getSupplierCode().
                        replace("Sup-", "")) + 1)
                : "Sup-001";
    }
}
