package com.todocode.bazaar.dto;


import com.todocode.bazaar.models.enums.ProductCategory;
import jakarta.validation.constraints.*;

public record CreateProductDTO(
        @NotBlank(message = "Name is required.")
        @Size(max = 30, message = "Name must not exceed 30 characters.")
        String name,

        @NotBlank(message = "Brand is required.")
        @Size(max = 30, message = "Brand must not exceed 30 characters.")
        String brand,

        @NotBlank(message = "Category is required.")
        String category,

        @NotNull(message = "Sale price is required.")
        @Positive(message = "Sale price must be greater than 0.")
        Double salePrice,

        @NotNull(message = "Stock is required.")
        @PositiveOrZero(message = "Stock cannot be negative.")
        Integer stock
) { }
