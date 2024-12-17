package com.todocode.bazaar.services.validator;


import com.todocode.bazaar.dto.CreateSupplierDTO;
import com.todocode.bazaar.dto.ModifySupplierDTO;
import com.todocode.bazaar.exceptions.BusinessException;
import com.todocode.bazaar.exceptions.NotFoundException;
import com.todocode.bazaar.models.Supplier;
import com.todocode.bazaar.models.enums.Status;
import com.todocode.bazaar.repository.ISupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupplierValidator {

    @Autowired
    ISupplierRepository supplierRepository;


    public void ValidateCreateSupplier(CreateSupplierDTO supplierDTO){

        if(supplierRepository.existsByBusinessName(supplierDTO.businessName()) ){
            throw new BusinessException("Supplier with business name " + supplierDTO.businessName() + " already exists.");
        }

        if(supplierRepository.existsByContact(supplierDTO.contact()) ){
            throw new BusinessException("Supplier with contact " + supplierDTO.contact() + " already exists.");
        }

    }

    public void ValidateUpdateSupplier(ModifySupplierDTO supplierDTO){

        Supplier supplierAux = supplierRepository.findById(supplierDTO.id())
                                                    .orElseThrow( () -> new NotFoundException("Supplier not found. ID: " + supplierDTO.id()));


        if( supplierDTO.contact() != null && !supplierAux.getContact().equals(supplierDTO.contact())){
            if(supplierRepository.existsByContact(supplierDTO.contact())){
                throw new BusinessException("Supplier with contact " + supplierDTO.contact() + " already exists.");
            }
        }

        if( supplierDTO.businessName() != null && !supplierAux.getBusinessName().equals(supplierDTO.businessName())){
            if(supplierRepository.existsByBusinessName(supplierDTO.businessName())){
                throw new BusinessException("Supplier with business name " + supplierDTO.businessName() + " already exists.");
            }
        }

        if(supplierDTO.status() != null){
            try{
                Status status = Status.valueOf(supplierDTO.status().toUpperCase());

                if (status != Status.ACTIVE && status != Status.INACTIVE) {
                    throw new BusinessException("Only ACTIVE and INACTIVE status are valid for update. Use delete endpoint instead for logical deletion.");
                }

            }catch(IllegalArgumentException ex) {
                throw new IllegalArgumentException("Invalid status: " + supplierDTO.status());
            }
        }


    }








}
