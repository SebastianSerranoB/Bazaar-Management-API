package com.todocode.bazaar.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateSupplyOrderDetailDTO(

        @NotNull(message = "Product ID is required.")
        @Positive(message = "Product ID must be a positive number.")
        Long productId,

        @NotNull(message = "Unit price is required.")
        @Positive(message = "Unit price must be a positive number.")
        Double unitPrice,

        @NotNull(message = "Quantity is required.")
        @Positive(message = "Quantity must be a positive number.")
        Integer quantity

) {
}
