package com.todocode.bazaar.mapper;


import com.todocode.bazaar.dto.CreateSupplierDTO;
import com.todocode.bazaar.dto.ModifySupplierDTO;
import com.todocode.bazaar.dto.SupplierResponseDTO;
import com.todocode.bazaar.models.Supplier;
import com.todocode.bazaar.models.enums.ProductCategory;
import com.todocode.bazaar.models.enums.Status;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {

    public Supplier toEntity(CreateSupplierDTO supplierDTO){
        Supplier supplierAux = new Supplier();

        supplierAux.setContact(supplierDTO.contact());
        supplierAux.setLocation(supplierDTO.location());
        supplierAux.setBusinessName(supplierDTO.businessName());

        supplierAux.setStatus(Status.ACTIVE);

        return supplierAux;
    }

    public SupplierResponseDTO toDTO(Supplier supplier) {
        return new SupplierResponseDTO(
                supplier.getId(),
                supplier.getBusinessName(),
                supplier.getLocation(),
                supplier.getContact(),
                supplier.getStatus()
        );
    }

    public Supplier updateEntity(Supplier supplier, ModifySupplierDTO supplierDTO){
        if (supplierDTO.businessName() != null) supplier.setBusinessName(supplierDTO.businessName());
        if (supplierDTO.contact() != null) supplier.setContact(supplierDTO.contact());
        if (supplierDTO.location() != null) supplier.setLocation(supplierDTO.location());
        if (supplierDTO.status() != null) supplier.setStatus(Status.valueOf(supplierDTO.status().toUpperCase()));

        return supplier;
    }





}
