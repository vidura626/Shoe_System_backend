package com.codeventlk.helloshoemanagementsystem.conversion;

import com.codeventlk.helloshoemanagementsystem.dto.*;
import com.codeventlk.helloshoemanagementsystem.entity.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ConversionData {
    final private ModelMapper modelMapper;
    public CustomerDTO convertToCustomerDTO(Optional<CustomerEntity> customerEntity){
        return modelMapper.map(customerEntity, CustomerDTO.class);
    }

    public CustomerEntity convertToCustomerEntity(Optional<CustomerDTO> customerDTO){
        return modelMapper.map(customerDTO, CustomerEntity.class);
    }

    public List<CustomerDTO> getCustomerDTOList(List<CustomerEntity> customerEntities){
        return modelMapper.map(customerEntities,List.class);
    }

    public List<CustomerEntity> getCustomerEntityList(List<CustomerEntity> customerDtos){
        return modelMapper.map(customerDtos,List.class);
    }
    public SupplierDTO convertToSupplierDTO(Optional<SupplierEntity> supplierEntity){
        return modelMapper.map(supplierEntity, SupplierDTO.class);
    }

    public SupplierEntity convertToSupplierEntity(Optional<SupplierDTO> supplierDTO){
        return modelMapper.map(supplierDTO, SupplierEntity.class);
    }

    public List<SupplierDTO> getSupplierDTOList(List<SupplierEntity> supplierEntities){
        return modelMapper.map(supplierEntities,List.class);
    }

    public List<SupplierEntity> getSupplierEntityList(List<SupplierEntity> supplierDTOs){
        return modelMapper.map(supplierDTOs,List.class);
    }

    public UserEntity toUserEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, UserEntity.class);
    }

    public EmployeeEntity toEmployeeEntity(EmployeeDTO employeeDTO){
        return modelMapper.map(employeeDTO, EmployeeEntity.class);
    }

    public EmployeeDTO toEmployeeDTO(Optional<EmployeeEntity> employeeEntity){
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public List<EmployeeDTO> toEmployeeDTOList(List<EmployeeEntity> employeeEntities) {
        return modelMapper.map(employeeEntities,List.class);
    }

    public GenderEntity toGenderEntity(GenderDTO genderDTO) {
        return modelMapper.map(genderDTO, GenderEntity.class);
    }

    public GenderDTO toGenderDTO(GenderEntity genderEntity){
        return modelMapper.map(genderEntity, GenderDTO.class);
    }

    public List<GenderDTO> convertToGenderDTO(List<GenderEntity> genderEntities) {
        return modelMapper.map(genderEntities,List.class);
    }

    public OccasionEntity toOccasionEntity(OccasionDTO occasionDTO) {
        return modelMapper.map(occasionDTO,OccasionEntity.class);
    }

    public OccasionDTO toOccasionDTO(OccasionEntity occasionEntity){
        return modelMapper.map(occasionEntity, OccasionDTO.class);
    }

    public List<OccasionDTO> convertToOccasionDTO(List<OccasionEntity> occasionEntities) {
        return modelMapper.map(occasionEntities,List.class);
    }

    public VarietyEntity toVarietyEntity(VarietyDTO varietyDTO) {
        return modelMapper.map(varietyDTO,VarietyEntity.class);
    }

    public List<VarietyDTO> convertToVarietyDTO(List<VarietyEntity> all) {
        return modelMapper.map(all,List.class);
    }

    public ItemEntity toItemEntity(ItemDTO itemDTO){
        return modelMapper.map(itemDTO, ItemEntity.class);
    }

    public ItemDTO toItemDTO(Optional<ItemEntity> itemEntity) {
        return modelMapper.map(itemEntity, ItemDTO.class);
    }

    public BranchEntity toBranchEntity(BranchDTO branchDTO) {
        return modelMapper.map(branchDTO, BranchEntity.class);
    }

    public SizeEntity toSizeEntity(SizeDTO sizeDTO) {
        return modelMapper.map(sizeDTO, SizeEntity.class);
    }

    public List<SizeDTO> convertToSizeDTOs(List<SizeEntity> sizeEntities) {
        return modelMapper.map(sizeEntities, List.class);
    }
}
