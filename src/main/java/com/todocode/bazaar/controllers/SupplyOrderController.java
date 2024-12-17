package com.todocode.bazaar.controllers;

import com.todocode.bazaar.dto.*;
import com.todocode.bazaar.services.ISupplyOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class SupplyOrderController {

    @Autowired
    ISupplyOrderService supplyOrderService;


    @GetMapping("/supplyOrder/getAll")
    public List<SupplyOrderResponseDTO> getSupplyOrders() {
        return supplyOrderService.getAllSupplies();
    }

    @GetMapping("/supplyOrder/findById/{id}")
    public SupplyOrderResponseDTO findById(@PathVariable Long id) {
        return supplyOrderService.findByIdResponse(id);
    }

    @PostMapping("/supplyOrder/create")
    public ResponseEntity<String> createSupplyOrder(@RequestBody @Valid CreateSupplyOrderDTO supplyOrderDTO) {
        supplyOrderService.createSupply(supplyOrderDTO);
        return ResponseEntity.ok("Supply Order created successfully.");
    }

    @PutMapping("/supplyOrder/update")
    public ResponseEntity<String> updateSupplyOrder(@RequestBody @Valid ModifySupplyOrderDTO supplyOrderDTO) {
        supplyOrderService.updateSupply(supplyOrderDTO);
        return ResponseEntity.ok("Supply Order updated successfully.");
    }

    @DeleteMapping("/supplyOrder/delete/{id}")
    public ResponseEntity<String> deleteSupplyOrder(@PathVariable Long id) {
        supplyOrderService.deleteSupply(id);
        return  ResponseEntity.ok("Supply order deleted successfully.");
    }


}
