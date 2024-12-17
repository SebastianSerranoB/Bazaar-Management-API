package com.todocode.bazaar.controllers;


import com.todocode.bazaar.dto.CreateProductDTO;
import com.todocode.bazaar.dto.ModifyProductDTO;
import com.todocode.bazaar.dto.ProductResponseDTO;
import com.todocode.bazaar.models.Product;
import com.todocode.bazaar.services.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {


    @Autowired
    private IProductService productService;


    @PostMapping("/product/create")
    public ResponseEntity<?> createProduct(@Valid @RequestBody CreateProductDTO productDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        productService.createProduct(productDTO);
        return ResponseEntity.ok("Product created successfully");
    }


    @GetMapping("/product/getAll")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProductsResponse());
    }



    @GetMapping("/product/findOne/{id}")
    public ResponseEntity<ProductResponseDTO> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductByIdResponse(id));
    }


    @PutMapping("/product/update")
    public ResponseEntity<?> editProduct(@Valid @RequestBody ModifyProductDTO product, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        productService.updateProduct(product);
        return ResponseEntity.ok("Product modified successfully.");
    }

    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id)
    {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully.");
    }


}
