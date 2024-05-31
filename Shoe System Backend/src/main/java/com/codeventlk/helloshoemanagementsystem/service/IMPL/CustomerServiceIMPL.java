package com.codeventlk.helloshoemanagementsystem.service.IMPL;

import com.codeventlk.helloshoemanagementsystem.conversion.ConversionData;
import com.codeventlk.helloshoemanagementsystem.dto.CustomerDTO;
import com.codeventlk.helloshoemanagementsystem.entity.CustomerEntity;
import com.codeventlk.helloshoemanagementsystem.Enum.Level;
import com.codeventlk.helloshoemanagementsystem.exception.NotFoundException;
import com.codeventlk.helloshoemanagementsystem.repository.CustomerServiceDao;
import com.codeventlk.helloshoemanagementsystem.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceIMPL implements CustomerService {

    final private ConversionData conversionData;

    final private CustomerServiceDao customerServiceDao;
    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        customerDTO.setCustomerId(getNextCustomerId());
        customerDTO.setLevel(Level.NEW);
        customerDTO.setTotalPoint(0);
        customerDTO.setJoinDate(new Date());
        /*customerDTO.setRecentPurchasedDate(new Timestamp(System.currentTimeMillis()));*/
        CustomerEntity customerEntity = conversionData.convertToCustomerEntity(Optional.of(customerDTO));
        customerServiceDao.save(customerEntity);
    }

    @Override
    public CustomerDTO getCustomer(String id) {
        if (!customerServiceDao.existsById(id)) throw new NotFoundException("Customer Not Found");
        return conversionData.convertToCustomerDTO(customerServiceDao.findById(id));
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return conversionData.getCustomerDTOList(customerServiceDao.findAll());
    }

    @Override
    public void deleteCustomer(String id) {
        if (!customerServiceDao.existsById(id)) throw new NotFoundException("Customer Not Found");
        customerServiceDao.deleteById(id);
    }

    @Override
    public void updateCustomer(String id,CustomerDTO customerDTO) {
        Optional<CustomerEntity> customerEntity = customerServiceDao.findById(id);
        if (customerEntity.isEmpty()) throw new NotFoundException("Customer Not Found");
        customerEntity.get().setCustomerName(customerDTO.getCustomerName());
        customerEntity.get().setGender(customerDTO.getGender());
       /* customerEntity.get().setJoinDate(customerDTO.getJoinDate());*/
        customerEntity.get().setDob(customerDTO.getDob());
        customerEntity.get().setAddress1(customerDTO.getAddress1());
        customerEntity.get().setAddress2(customerDTO.getAddress2());
        customerEntity.get().setAddress3(customerDTO.getAddress3());
        customerEntity.get().setAddress4(customerDTO.getAddress4());
        customerEntity.get().setPostalCode(customerDTO.getPostalCode());
        customerEntity.get().setContactNo(customerDTO.getContactNo());
        customerEntity.get().setEmail(customerDTO.getEmail());
        /*customerEntity.get().setRecentPurchasedDate(customerDTO.getRecentPurchasedDate());*/
    }

    @Override
    public String getCustomerId() {
        return getNextCustomerId();
    }

    private String getNextCustomerId() {
        CustomerEntity firstByOrderByCustomerIdDesc = customerServiceDao.findFirstByOrderByCustomerIdDesc();
        return (firstByOrderByCustomerIdDesc != null)
                ? String.format("Cust-%03d",
                        Integer.parseInt(firstByOrderByCustomerIdDesc.getCustomerId().
                                replace("Cust-", "")) + 1)
                : "Cust-001";
    }
}
