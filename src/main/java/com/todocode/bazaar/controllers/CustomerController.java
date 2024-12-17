package com.todocode.bazaar.controllers;

import com.todocode.bazaar.dto.CreateCustomerDTO;
import com.todocode.bazaar.dto.CustomerResponseDTO;
import com.todocode.bazaar.dto.ModifyCustomerDTO;
import com.todocode.bazaar.dto.ModifyProductDTO;
import com.todocode.bazaar.services.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    ICustomerService customerService;


    @GetMapping("/customer/getAll")
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers() );
    }


    @GetMapping("/customer/findById/{id}")
    public ResponseEntity<CustomerResponseDTO> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerByIdResponse(id));
    }


    @PostMapping("/customer/create")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CreateCustomerDTO customerDTO) {
        customerService.createCustomer(customerDTO);
        return ResponseEntity.ok("Customer created successfully.");
    }


    @PutMapping("/customer/update")
    public ResponseEntity<String> updateCustomer(@RequestBody @Valid ModifyCustomerDTO customerDTO) {
        customerService.updateCustomer(customerDTO);
        return ResponseEntity.ok("Customer updated successfully.");
    }



    @DeleteMapping("/customer/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomers(id);
        return ResponseEntity.ok("Customer deleted. ID " + id);
    }


}
