package com.todocode.bazaar.dto;


import com.todocode.bazaar.models.enums.ProductCategory;
import com.todocode.bazaar.models.enums.Status;

public record ProductResponseDTO(Long id, String name, String brand, ProductCategory category,
                                 Double salePrice, Integer stock, Status status){}
