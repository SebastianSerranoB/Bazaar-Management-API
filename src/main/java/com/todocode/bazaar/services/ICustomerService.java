package com.todocode.bazaar.services;


import com.todocode.bazaar.dto.CreateCustomerDTO;
import com.todocode.bazaar.dto.CustomerResponseDTO;
import com.todocode.bazaar.dto.ModifyCustomerDTO;
import com.todocode.bazaar.models.Customer;

import java.util.List;

public interface ICustomerService {

    public List<CustomerResponseDTO> getAllCustomers();

    public void createCustomer(CreateCustomerDTO customerDTO);

    public Customer getCustomerById(Long id);

    public CustomerResponseDTO getCustomerByIdResponse(Long id);

    public void updateCustomer(ModifyCustomerDTO customerDTO);

    public void deleteCustomers(Long id);
}
