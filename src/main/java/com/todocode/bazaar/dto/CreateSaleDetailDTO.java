package com.todocode.bazaar.dto;

import jakarta.validation.constraints.*;

import java.util.List;


public record CreateSaleDetailDTO(

        @NotNull(message = "Product ID is required.")
        @Positive(message = "Product ID must be a positive number.")
         Long productId,

        @NotNull(message = "Product quantity is required.")
        @Positive(message = "Product quantity must be a positive number.")
         Integer productQuantity
){}
