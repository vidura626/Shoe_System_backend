package com.example.cw_spring.service;

import com.example.cw_spring.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    boolean saveEmployee(EmployeeDTO employeeDTO, String password);
    boolean updateEmployee(String id, EmployeeDTO employeeDTO, String password);
    List<EmployeeDTO> getAllEmployee();
    boolean deleteEmployee(String email);
    EmployeeDTO getEmployee(String email);
}
