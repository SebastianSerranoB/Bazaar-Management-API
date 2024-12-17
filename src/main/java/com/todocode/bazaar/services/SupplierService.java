package com.todocode.bazaar.services;


import com.todocode.bazaar.dto.CreateSupplierDTO;
import com.todocode.bazaar.dto.ModifySupplierDTO;
import com.todocode.bazaar.dto.SupplierResponseDTO;
import com.todocode.bazaar.exceptions.NotFoundException;
import com.todocode.bazaar.mapper.SupplierMapper;
import com.todocode.bazaar.models.Supplier;
import com.todocode.bazaar.models.enums.Status;
import com.todocode.bazaar.repository.ISupplierRepository;
import com.todocode.bazaar.services.validator.SupplierValidator;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierService implements ISupplierService {

    @Autowired
    ISupplierRepository supplierRepository;

    @Autowired
    SupplierMapper supplierMapper;

    @Autowired
    SupplierValidator supplierValidator;


    @Override
    public List<SupplierResponseDTO> getAllSuppliers() {
        return supplierRepository.findAll()
                .stream()
                .map(supplier -> supplierMapper.toDTO(supplier))
                .collect(Collectors.toList());
    }

    @Override
    public void createSupplier(CreateSupplierDTO supplierDTO) {
        supplierValidator.ValidateCreateSupplier(supplierDTO);
        supplierRepository.save(supplierMapper.toEntity(supplierDTO));
    }

    @Override
    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id).orElse(null);
    }

    @Override
    public SupplierResponseDTO getSupplierResponseById(Long id) {
        return supplierMapper.toDTO(supplierRepository.findById(id)
                                                       .orElseThrow( () -> new NotFoundException("Supplier not found. ID: " + id)));
    }

    @Override
    public void updateSupplier(ModifySupplierDTO supplierDTO) {
        supplierValidator.ValidateUpdateSupplier(supplierDTO);
        supplierRepository.save(supplierMapper
                                             .updateEntity(this.getSupplierById(supplierDTO.id()),supplierDTO));
    }

    @Override
    public void deleteSupplier(Long id) {
        Supplier supplierAux = supplierRepository.findById(id).orElseThrow( () -> new NotFoundException("Supplier not found. ID: " + id));
        supplierAux.setStatus(Status.DELETED);
        supplierRepository.save(supplierAux);
    }
}
