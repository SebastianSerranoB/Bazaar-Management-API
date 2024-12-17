package com.todocode.bazaar.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ModifySupplierDTO(

        @NotNull(message = "Id is required.")
        @Positive(message = "Id must be a positive number.")
        Long id,

        @Size(min = 4, max = 40, message = "Business name must not exceed 40 characters")
        String businessName,

        @Size(min = 4, max = 20, message = "Contact  must not exceed 20 characters")
        String contact,

        @Size(min = 4, max = 40, message = "Location name must not exceed 40 characters")
        String location,

        @Size(min = 4, max = 20, message = "Status  must not exceed 20 characters")
        String status
) {
}
