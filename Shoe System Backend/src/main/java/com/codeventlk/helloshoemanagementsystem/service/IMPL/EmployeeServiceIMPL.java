package com.codeventlk.helloshoemanagementsystem.service.IMPL;

import com.codeventlk.helloshoemanagementsystem.conversion.ConversionData;
import com.codeventlk.helloshoemanagementsystem.dto.EmployeeDTO;
import com.codeventlk.helloshoemanagementsystem.entity.EmployeeEntity;
import com.codeventlk.helloshoemanagementsystem.entity.UserEntity;
import com.codeventlk.helloshoemanagementsystem.exception.NotFoundException;
import com.codeventlk.helloshoemanagementsystem.repository.EmployeeServiceDao;
import com.codeventlk.helloshoemanagementsystem.repository.UserServiceDao;
import com.codeventlk.helloshoemanagementsystem.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class EmployeeServiceIMPL implements EmployeeService {

    private final EmployeeServiceDao employeeServiceDao;
    private final UserServiceDao userServiceDao;
    private final ConversionData conversionData;
    @Override
    public void saveEmployee(EmployeeDTO employeeDTO) {
        employeeDTO.setEmployeeCode(getNextEmployeeCode());
        EmployeeEntity employeeEntity = conversionData.toEmployeeEntity(employeeDTO);

        String email = employeeDTO.getEmail();
        Optional<UserEntity> byEmail = userServiceDao.findByEmail(email);

        if (byEmail == null) {
            throw new NotFoundException("User Not Found");
        }

        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setId(byEmail.get().getId());
        newUserEntity.setEmail(email);
        newUserEntity.setPassword(byEmail.get().getPassword());
        newUserEntity.setRole(byEmail.get().getRole());

        employeeEntity.setUserEntity(newUserEntity);

        employeeServiceDao.save(employeeEntity);
    }

    @Override
    public EmployeeDTO getEmployee(String id) {
       if(!employeeServiceDao.existsById(id)){throw new NotFoundException("Employee Not Found");}
       return conversionData.toEmployeeDTO(employeeServiceDao.findById(id));
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        return conversionData.toEmployeeDTOList(employeeServiceDao.findAll());
    }

    @Override
    public void updateEmployee(String id, EmployeeDTO employeeDTO) {
        if(!employeeServiceDao.existsById(id)){throw new NotFoundException("Employee Not Found");}
        Optional<EmployeeEntity> employeeEntity = employeeServiceDao.findById(id);
        EmployeeEntity employee = employeeEntity.get();
        employee.setEmployeeName(employeeDTO.getEmployeeName());
        employee.setProfilePic(employeeDTO.getProfilePic());
        employee.setGender(employeeDTO.getGender());
        employee.setStatus(employeeDTO.getStatus());
        employee.setDesignation(employeeDTO.getDesignation());
        employee.setDateOfBirth(employeeDTO.getDateOfBirth());
        employee.setDateOfJoin(employeeDTO.getDateOfJoin());
        employee.setAttachedBranch(employeeDTO.getAttachedBranch());
        employee.setAddress1(employeeDTO.getAddress1());
        employee.setAddress2(employeeDTO.getAddress2());
        employee.setAddress3(employeeDTO.getAddress3());
        employee.setAddress4(employeeDTO.getAddress4());
        employee.setPostalCode(employeeDTO.getPostalCode());
        employee.setContactNo(employeeDTO.getContactNo());
        employee.setEmail(employeeDTO.getEmail());
        employee.setEmergencyContactName(employeeDTO.getEmergencyContactName());
        employee.setEmergencyContact(employeeDTO.getEmergencyContact());
    }

    @Override
    public void deleteEmployee(String id) {
        if(!employeeServiceDao.existsById(id)){throw new NotFoundException("Employee Not Found");}
        employeeServiceDao.deleteById(id);
    }


    private String getNextEmployeeCode() {
        EmployeeEntity employeeEntity = employeeServiceDao.findFirstByOrderByEmployeeCodeDesc();
        return (employeeEntity != null)
                ? String.format("Emp-%03d",
                Integer.parseInt(employeeEntity.getEmployeeCode()
                        .replace("Emp-", "")) + 1)
                : "Emp-001";
    }
}
