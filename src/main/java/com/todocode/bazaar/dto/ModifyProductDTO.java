package com.todocode.bazaar.dto;

import com.todocode.bazaar.models.enums.ProductCategory;
import jakarta.validation.constraints.*;

public record ModifyProductDTO(

        @NotNull
        @Positive
        Long id,

        @Size(max = 30, message = "Name must not exceed 30 characters.")
        String name,

        @Size(max = 30, message = "Brand must not exceed 30 characters.")
        String brand,

        @Size(min = 5, max = 30, message = "Category must not exceed 30 characters" )
        String category,

        @Positive(message = "Sale price must be greater than 0.")
        Double salePrice,

        @PositiveOrZero(message = "Stock cannot be negative.")
        Integer stock,

        @NotNull(message = "status must not be null.")
        String status
) { }

