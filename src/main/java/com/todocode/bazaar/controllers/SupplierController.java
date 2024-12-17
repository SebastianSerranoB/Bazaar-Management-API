package com.todocode.bazaar.controllers;

import com.todocode.bazaar.dto.CreateSupplierDTO;
import com.todocode.bazaar.dto.ModifySupplierDTO;
import com.todocode.bazaar.dto.SupplierResponseDTO;
import com.todocode.bazaar.models.Supplier;
import com.todocode.bazaar.services.ISupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
public class SupplierController {

    @Autowired
    ISupplierService supplierService;


    @GetMapping("/supplier/getAll")
    public List<SupplierResponseDTO> getSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/supplier/findOne/{id}")
    public SupplierResponseDTO findOne(@PathVariable Long id) {
        return supplierService.getSupplierResponseById(id);
    }

    @PostMapping("/supplier/create")
    public ResponseEntity<String> createSupplier(@RequestBody @Valid CreateSupplierDTO supplierDTO) {
        supplierService.createSupplier(supplierDTO);
        return ResponseEntity.ok("Supplier created successfully");
    }

    @PutMapping("/supplier/update")
    public ResponseEntity<String> editSupplier(@RequestBody @Valid ModifySupplierDTO supplierDTO) {
        supplierService.updateSupplier(supplierDTO);
        return ResponseEntity.ok("Supplier updated successfully.");
    }

    @DeleteMapping("/supplier/delete/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.ok("Supplier deleted successfully.");
    }


}
