package com.example.cw_spring.service;

import com.example.cw_spring.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    boolean saveCustomer(CustomerDTO customerDTO);
    boolean updateCustomer(String id, CustomerDTO customerDTO);
    boolean deleteCustomer(String id);
    List<CustomerDTO> getAllCustomer();
    CustomerDTO getSelectCustomer(String email);
    List<String> getAllCustomerIds();
    String generateNextID();
    CustomerDTO getCustomer(String id);

}
