package com.example.cw_spring.controller;

import com.example.cw_spring.dto.EmployeeDTO;
import com.example.cw_spring.enums.Gender;
import com.example.cw_spring.enums.Role;
import com.example.cw_spring.service.EmployeeService;
import com.example.cw_spring.util.UtilMatters;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/health")
    public String healthTest() {
        return "Health check passed";
    }

    @GetMapping(produces = "application/json")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployee();
    }
    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean saveEmployee(
             @RequestPart("employee_name") String employee_name,
             @RequestPart("employee_profile_pic") String employee_profile_pic,
             @RequestPart("gender") String gender,
             @RequestPart("status") String status,
             @RequestPart("designation") String designation,
             @RequestPart("access_role") String access_role,
             @RequestPart("dob") String dob,
             @RequestPart("date_of_join") String date_of_join,
             @RequestPart("attached_branch") String attached_branch,
             @RequestPart("address_line_01") String address_line_01,
             @RequestPart("address_line_02") String address_line_02,
             @RequestPart("address_line_03") String address_line_03,
             @RequestPart("address_line_04") String address_line_04,
             @RequestPart("address_line_05") String address_line_05,
             @RequestPart("contact_no") String contact_no,
             @RequestPart("email") String email,
             @RequestPart("in_case_of_emergency") String in_case_of_emergency,
             @RequestPart("emergency_contact_no") String emergency_contact_no,
             @RequestPart("password") String password) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployee_name(employee_name);
        employeeDTO.setEmployee_profile_pic(UtilMatters.convertBase64(employee_profile_pic));
        employeeDTO.setGender(Gender.valueOf(gender));
        employeeDTO.setStatus(status);
        employeeDTO.setDesignation(designation);
        employeeDTO.setAccess_role(Role.valueOf(access_role));
        employeeDTO.setDob(dateFormat.parse(dob));
        employeeDTO.setDate_of_join(dateFormat.parse(date_of_join));
        employeeDTO.setAttached_branch(attached_branch);
        employeeDTO.setAddress_line_01(address_line_01);
        employeeDTO.setAddress_line_02(address_line_02);
        employeeDTO.setAddress_line_03(address_line_03);
        employeeDTO.setAddress_line_04(address_line_04);
        employeeDTO.setAddress_line_05(address_line_05);
        employeeDTO.setContact_no(contact_no);
        employeeDTO.setEmail(email);
        employeeDTO.setIn_case_of_emergency(in_case_of_emergency);
        employeeDTO.setEmergency_contact_no(emergency_contact_no);
        employeeDTO.setEmployee_code(UUID.randomUUID().toString());

        return employeeService.saveEmployee(employeeDTO, password);
    }

    @PutMapping(value = "/update",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public boolean updateEmployee(
            @RequestPart("employee_code") String employee_code,
            @RequestPart("employee_name") String employee_name,
            @RequestPart("employee_profile_pic") String employee_profile_pic,
            @RequestPart("gender") String gender,
            @RequestPart("status") String status,
            @RequestPart("designation") String designation,
            @RequestPart("access_role") String access_role,
            @RequestPart("dob") String dob,
            @RequestPart("date_of_join") String date_of_join,
            @RequestPart("attached_branch") String attached_branch,
            @RequestPart("address_line_01") String address_line_01,
            @RequestPart("address_line_02") String address_line_02,
            @RequestPart("address_line_03") String address_line_03,
            @RequestPart("address_line_04") String address_line_04,
            @RequestPart("address_line_05") String address_line_05,
            @RequestPart("contact_no") String contact_no,
            @RequestPart("email") String email,
            @RequestPart("in_case_of_emergency") String in_case_of_emergency,
            @RequestPart("emergency_contact_no") String emergency_contact_no,
            @RequestPart("password") String password) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployee_name(employee_name);
        employeeDTO.setEmployee_profile_pic(UtilMatters.convertBase64(employee_profile_pic));
        employeeDTO.setGender(Gender.valueOf(gender));
        employeeDTO.setStatus(status);
        employeeDTO.setDesignation(designation);
        employeeDTO.setAccess_role(Role.valueOf(access_role));
        employeeDTO.setDob(dateFormat.parse(dob));
        employeeDTO.setDate_of_join(dateFormat.parse(date_of_join));
        employeeDTO.setAttached_branch(attached_branch);
        employeeDTO.setAddress_line_01(address_line_01);
        employeeDTO.setAddress_line_02(address_line_02);
        employeeDTO.setAddress_line_03(address_line_03);
        employeeDTO.setAddress_line_04(address_line_04);
        employeeDTO.setAddress_line_05(address_line_05);
        employeeDTO.setContact_no(contact_no);
        employeeDTO.setEmail(email);
        employeeDTO.setIn_case_of_emergency(in_case_of_emergency);
        employeeDTO.setEmergency_contact_no(emergency_contact_no);

        return employeeService.updateEmployee(employee_code, employeeDTO, password);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public boolean deleteEmployee(String email) {
        return employeeService.deleteEmployee(email);
    }

    @GetMapping
    public EmployeeDTO getEmployee(String email) {
        return employeeService.getEmployee(email);
    }
}
