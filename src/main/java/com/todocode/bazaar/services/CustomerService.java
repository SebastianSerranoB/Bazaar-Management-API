package com.todocode.bazaar.services;

import com.todocode.bazaar.dto.CreateCustomerDTO;
import com.todocode.bazaar.dto.CustomerResponseDTO;
import com.todocode.bazaar.dto.ModifyCustomerDTO;
import com.todocode.bazaar.exceptions.BusinessException;
import com.todocode.bazaar.exceptions.NotFoundException;
import com.todocode.bazaar.mapper.CustomerMapper;
import com.todocode.bazaar.models.Customer;
import com.todocode.bazaar.models.enums.Status;
import com.todocode.bazaar.repository.ICustomerRepository;
import com.todocode.bazaar.services.validator.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    ICustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    CustomerValidator customerValidator;


    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerRepository.findAll()
                                 .stream()
                                 .map(customer -> customerMapper.toDTO(customer))
                                 .collect(Collectors.toList());
    }


    @Override
    public void createCustomer(CreateCustomerDTO customerDTO) {
        customerValidator.validateCreateCustomer(customerDTO);
        customerRepository.save(customerMapper.toEntity(customerDTO));
    }

    @Override
    public CustomerResponseDTO getCustomerByIdResponse(Long id) {
        return  customerMapper.toDTO(customerRepository.findById(id)
                                                        .orElseThrow(() -> new NotFoundException("Customer not found. ID: " + id)) );
    }

    @Override
    public Customer getCustomerById(Long id){
        return customerRepository.findById(id).orElse(null);
    }


    @Override
    public void updateCustomer(ModifyCustomerDTO customerDTO) {
        customerValidator.validateUpdateCustomer(customerDTO);
        customerRepository.save(customerMapper.updateEntity(
                                                            this.getCustomerById(customerDTO.id()), customerDTO));
    }

    @Override
    public void deleteCustomers(Long id) {
        Customer customerAux = customerRepository.findById(id)
                                                  .orElseThrow(() -> new NotFoundException("Customer not found. ID: " + id));

        customerAux.setStatus(Status.INACTIVE);
        customerRepository.save(customerAux);
    }

}
