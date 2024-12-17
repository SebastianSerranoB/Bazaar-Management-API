package com.todocode.bazaar.services;

import com.todocode.bazaar.dto.CreateSupplierDTO;
import com.todocode.bazaar.dto.ModifySupplierDTO;
import com.todocode.bazaar.dto.SupplierResponseDTO;
import com.todocode.bazaar.models.Supplier;

import java.util.List;

public interface ISupplierService {


    public List<SupplierResponseDTO> getAllSuppliers();

    public void createSupplier(CreateSupplierDTO supplierDTO);

    public Supplier getSupplierById(Long id);

    public SupplierResponseDTO getSupplierResponseById(Long id);

    public void updateSupplier(ModifySupplierDTO supplierDTO);

    public void deleteSupplier(Long id);

}
