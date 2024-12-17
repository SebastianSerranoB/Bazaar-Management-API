package com.todocode.bazaar.mapper;

import com.todocode.bazaar.dto.CreateCustomerDTO;
import com.todocode.bazaar.dto.CustomerResponseDTO;
import com.todocode.bazaar.dto.ModifyCustomerDTO;
import com.todocode.bazaar.dto.ModifyProductDTO;
import com.todocode.bazaar.models.Customer;
import com.todocode.bazaar.models.Product;
import com.todocode.bazaar.models.enums.ProductCategory;
import com.todocode.bazaar.models.enums.Status;
import org.springframework.stereotype.Component;


@Component
public class CustomerMapper {


    public CustomerResponseDTO toDTO(Customer customer) {
        return new CustomerResponseDTO(
                customer.getId(),
                customer.getName(),
                customer.getSurname(),
                customer.getGender(),
                customer.getBirthDate(),
                customer.getNationalId(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getStatus()
        );
    }

    public Customer toEntity(CreateCustomerDTO customerDTO) {
        Customer customer = new Customer();

        customer.setName(customerDTO.name());
        customer.setSurname(customerDTO.surname());
        customer.setBirthDate(customerDTO.birthDate());
        customer.setEmail(customerDTO.email());
        customer.setGender(customerDTO.gender());
        customer.setAddress(customerDTO.address());
        customer.setNationalId(customerDTO.nationalId());
        customer.setPhone(customerDTO.phone());

        customer.setStatus(Status.ACTIVE);

        return customer;
    }


    public Customer updateEntity(Customer customer, ModifyCustomerDTO dto) {

        if (dto.name() != null) customer.setName(dto.name());
        if (dto.surname() != null) customer.setSurname(dto.surname());
        if (dto.phone() != null) customer.setPhone(dto.phone());
        if (dto.nationalId() != null) customer.setNationalId(dto.nationalId());
        if (dto.email() != null) customer.setEmail(dto.email());
        if (dto.address() != null) customer.setAddress(dto.address());
        if (dto.gender() != null) customer.setGender(dto.gender());
        if (dto.birthDate() != null) customer.setBirthDate(dto.birthDate());
        if (dto.status() != null) customer.setStatus(Status.valueOf(dto.status().toUpperCase()));

        return customer;
    }

}
