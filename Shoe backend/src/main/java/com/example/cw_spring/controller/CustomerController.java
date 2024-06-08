package com.example.cw_spring.controller;

import com.example.cw_spring.dto.CustomerDTO;
import com.example.cw_spring.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/health")
    public String healthTest() {
        return "Health check passed";
    }

    @PostMapping("/save")
    public boolean saveCustomer(@RequestBody CustomerDTO customerDTO) {
        customerDTO.setCustomer_code(customerService.generateNextID());
        return customerService.saveCustomer(customerDTO);
    }


    @PutMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean updateCustomer(@RequestBody CustomerDTO customerDTO) {
        System.out.println(">>>>>>>>> : "+customerDTO.getCustomer_code());
        return customerService.updateCustomer(customerDTO.getCustomer_code(), customerDTO);
    }

    @DeleteMapping("/delete")
    public boolean deleteCustomer(@RequestPart ("customer_code")String id) {
        return customerService.deleteCustomer(id);
    }

    @GetMapping("/getSelectCustomer")
    public CustomerDTO getSelectCustomer( String email) {
        return customerService.getSelectCustomer(email);
    }

    @GetMapping(produces = "application/json")
    public List<CustomerDTO> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @GetMapping("/getCustomer/{customer_code}")
    public CustomerDTO getCustomer(@PathVariable String customer_code) {
        return customerService.getCustomer(customer_code);
    }
    @GetMapping("/getAllCustomerIds")
    public List<String> getAllCustomerIds() {
        return customerService.getAllCustomerIds();
    }

}
