package com.codeventlk.helloshoemanagementsystem.service;

import com.codeventlk.helloshoemanagementsystem.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    void saveEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO getEmployee(String id);

    List<EmployeeDTO> getAllEmployee();

    void updateEmployee(String id,EmployeeDTO employeeDTO);

    void deleteEmployee(String id);
}
