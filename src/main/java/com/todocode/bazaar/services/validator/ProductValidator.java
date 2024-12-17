package com.todocode.bazaar.services.validator;

import com.todocode.bazaar.dto.CreateProductDTO;
import com.todocode.bazaar.dto.ModifyProductDTO;
import com.todocode.bazaar.exceptions.BusinessException;
import com.todocode.bazaar.exceptions.NotFoundException;
import com.todocode.bazaar.models.Product;
import com.todocode.bazaar.models.enums.ProductCategory;
import com.todocode.bazaar.models.enums.Status;
import com.todocode.bazaar.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ProductValidator {

    @Autowired
    private IProductRepository productRepository;

    public void validateCreateProduct(CreateProductDTO dto) {

        if (productRepository.existsByNameAndBrand(dto.name(), dto.brand() ) ) {
            throw new BusinessException("Product with name " + dto.name() + " and brand " + dto.brand() + " already exists.");
        }

        try {
            ProductCategory category = ProductCategory.valueOf(dto.category().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid category: " + dto.category() );
        }


    }

    public void validateUpdateProduct(ModifyProductDTO dto) {

        Product auxProduct = productRepository.findById(dto.id()).
                                                 orElseThrow( () -> new NotFoundException("Product not found. ID: " + dto.id()) );

        if(dto.brand() != null && dto.name() != null) {
            if(!auxProduct.getName().equalsIgnoreCase(dto.name()) || !auxProduct.getBrand().equalsIgnoreCase(dto.brand()) ) {
                if(productRepository.existsByNameAndBrand(dto.name(), dto.brand()) ) {
                    throw new BusinessException("Product with name " + dto.name() + " and brand " + dto.brand() + " already exists.");
                }
            }
        }

        if(dto.category() != null) {
            try {
                ProductCategory category = ProductCategory.valueOf(dto.category().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid category: " + dto.category() );
            }
        }

        if(dto.status() != null) {
            try {
                Status status = Status.valueOf(dto.status().toUpperCase());

                if (status == Status.DELETED) {
                    throw new BusinessException("Status 'DELETED' is not allowed on updates. Use delete endpoint instead.");
                }

            } catch(IllegalArgumentException e){
                throw  new IllegalArgumentException("Invalid status: " + dto.status() );
            }
        }

    }






}

