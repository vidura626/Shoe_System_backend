package com.example.cw_spring.service.impl;

/*import com.example.cw_spring.controller.Employee;*/
import com.example.cw_spring.dto.EmployeeDTO;
import com.example.cw_spring.entity.EmployeeEntity;
import com.example.cw_spring.entity.UserEntity;
import com.example.cw_spring.enums.Role;
import com.example.cw_spring.repository.EmployeeDAO;
import com.example.cw_spring.repository.UserDAO;
import com.example.cw_spring.reqAndres.secure.SignUp;
import com.example.cw_spring.service.AuthenticationService;
import com.example.cw_spring.service.EmployeeService;
import com.example.cw_spring.util.Mapping;
import com.example.cw_spring.util.UtilMatters;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDAO employeeDAO;
    private final Mapping mapper;
    private final AuthenticationService authenticationService;
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    @Override
    public boolean saveEmployee(EmployeeDTO employeeDTO, String password) {
        EmployeeEntity employee = mapper.toEmployee(employeeDTO);
        EmployeeDTO saveEmployee = mapper.toEmployeeDTO(employeeDAO.save(employee));

        SignUp signUp = new SignUp();
        signUp.setEmail(saveEmployee.getEmail());
        signUp.setPassword(password);
        signUp.setRole(Role.valueOf(String.valueOf(employeeDTO.getAccess_role())));

        authenticationService.signUp(signUp, employeeDTO);

        return saveEmployee != null;
    }


    @Override
    public boolean updateEmployee(String id, EmployeeDTO employeeDTO, String password) {
        Optional<EmployeeEntity> employee = employeeDAO.findById(id);
        String email = employee.get().getEmail();
        if (employee.isPresent()) {
            employee.get().setEmployee_name(employeeDTO.getEmployee_name());
            employee.get().setEmployee_profile_pic(UtilMatters.convertBase64(employeeDTO.getEmployee_profile_pic()));
            employee.get().setGender(employeeDTO.getGender());
            employee.get().setStatus(employeeDTO.getStatus());
            employee.get().setDesignation(employeeDTO.getDesignation());
            employee.get().setAccess_role(employeeDTO.getAccess_role());
            employee.get().setDob(employeeDTO.getDob());
            employee.get().setDate_of_join(employeeDTO.getDate_of_join());
            employee.get().setAttached_branch(employeeDTO.getAttached_branch());
            employee.get().setAddress_line_01(employeeDTO.getAddress_line_01());
            employee.get().setAddress_line_02(employeeDTO.getAddress_line_02());
            employee.get().setAddress_line_03(employeeDTO.getAddress_line_03());
            employee.get().setAddress_line_04(employeeDTO.getAddress_line_04());
            employee.get().setAddress_line_05(employeeDTO.getAddress_line_05());
            employee.get().setContact_no(employeeDTO.getContact_no());
            employee.get().setEmail(employeeDTO.getEmail());
            employee.get().setIn_case_of_emergency(employeeDTO.getIn_case_of_emergency());
            employee.get().setEmergency_contact_no(employeeDTO.getEmergency_contact_no());

            Optional<UserEntity> user = userDAO.findByEmail(email);
            if (user.isPresent()) {
                user.get().setEmail(employeeDTO.getEmail());
                user.get().setPassword(passwordEncoder.encode(password));
                user.get().setRole(employeeDTO.getAccess_role());
            } else {
                throw new RuntimeException(email + " not found");
            }
        }
        return false;
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        return mapper.toEmployeeDTOList(employeeDAO.findAll());
    }

    @Override
    public boolean deleteEmployee(String email) {
        Optional<EmployeeEntity> employee = employeeDAO.findByEmail(email);
        Optional<UserEntity> user = userDAO.findByEmail(email);
        if (employee.isPresent() && user.isPresent()) {
            userDAO.delete(user.get());
            employeeDAO.delete(employee.get());
            return true;
        } else {
            throw new RuntimeException(email + " not found");
        }
    }

    @Override
    public EmployeeDTO getEmployee(String email) {
        Optional<EmployeeEntity> employee = employeeDAO.findByEmail(email);
        if (employee.isPresent()) {
            return mapper.toEmployeeDTO(employee.get());
        } else {
            throw new RuntimeException(email + " not found");
        }
    }
}
