package com.todocode.bazaar.services.validator;

import com.todocode.bazaar.dto.CreateCustomerDTO;
import com.todocode.bazaar.dto.ModifyCustomerDTO;
import com.todocode.bazaar.exceptions.BusinessException;
import com.todocode.bazaar.exceptions.NotFoundException;
import com.todocode.bazaar.models.Customer;
import com.todocode.bazaar.models.enums.Status;
import com.todocode.bazaar.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidator {

    @Autowired
    ICustomerRepository customerRepository;

    public void validateCreateCustomer(CreateCustomerDTO customerDTO)
    {
        if(customerRepository.existByNationalId(customerDTO.nationalId())) {
            throw new BusinessException("Customer with National ID: " + customerDTO.nationalId() + " already exists.");
        }

        if(customerRepository.existByEmail(customerDTO.email())) {
            throw new BusinessException("Customer with email: " + customerDTO.email() + " already exists");
        }

        if(customerRepository.existByPhone(customerDTO.phone())){
            throw new BusinessException("Customer with phone number: " + customerDTO.phone() + " already exists");
        }

    }

    public void validateUpdateCustomer(ModifyCustomerDTO customerDTO){

         Customer customerAux = customerRepository.findById(customerDTO.id())
                            .orElseThrow( () -> new NotFoundException("Customer not found. ID: " + customerDTO.id()));

        if(customerDTO.email() != null && !customerDTO.email().equalsIgnoreCase(customerAux.getEmail())) {
            if(customerRepository.existByEmail(customerDTO.email())) {
                throw new BusinessException("Customer with email: " + customerDTO.email() + " already exists");
            }
        }

        if(customerDTO.nationalId() != null && !customerDTO.nationalId().equalsIgnoreCase(customerAux.getNationalId())){
            if(customerRepository.existByNationalId(customerDTO.nationalId())) {
                throw new BusinessException("Customer with National ID: " + customerDTO.nationalId() + " already exists.");
            }
        }

        if(customerDTO.phone() != null && !customerDTO.phone().equalsIgnoreCase(customerAux.getPhone())){
            if(customerRepository.existByPhone(customerDTO.phone())){
                throw new BusinessException("Customer with phone number: " + customerDTO.phone() + " already exists");
            }
        }

        if(customerDTO.status() != null) {

            try{
                Status status = Status.valueOf(customerDTO.status().toUpperCase());

                if (status != Status.ACTIVE && status != Status.INACTIVE) {
                    throw new BusinessException("Only ACTIVE and INACTIVE status are valid for update. Use delete endpoint instead for logical deletion.");
                }

            }catch(IllegalArgumentException ex) {
                throw new IllegalArgumentException("Invalid status: " + customerDTO.status());
            }
        }

    }

}
