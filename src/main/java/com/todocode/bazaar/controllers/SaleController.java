package com.todocode.bazaar.controllers;

import com.todocode.bazaar.dto.CreateSaleDTO;
import com.todocode.bazaar.dto.ModifySaleDTO;
import com.todocode.bazaar.dto.SaleResponseDTO;
import com.todocode.bazaar.models.Sale;
import com.todocode.bazaar.services.ISaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SaleController {


    @Autowired
    ISaleService saleService;


    @GetMapping("/sale/getAll")
    public List<SaleResponseDTO> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/sale/findById/{id}")
    public SaleResponseDTO findOne(@PathVariable Long id) {
        return saleService.findSaleResponseById(id);
    }

    @PostMapping("/sale/create")
    public ResponseEntity<String> createSale(@RequestBody @Valid CreateSaleDTO saleDTO) {
        saleService.createSale(saleDTO);
        return ResponseEntity.ok("Sale created successfully.");
    }

    @PutMapping("/sale/update")
    public ResponseEntity<String> editSale(@RequestBody @Valid ModifySaleDTO saleDTO) {
        saleService.updateSale(saleDTO);
        return ResponseEntity.ok("Sale updated successfully.");
    }

    @DeleteMapping("/sale/delete/{id}")
    public ResponseEntity<String> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.ok("Sale deleted successfully.");
    }
}
