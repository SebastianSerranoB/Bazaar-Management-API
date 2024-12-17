package com.todocode.bazaar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateSupplierDTO(

        @NotBlank(message = "Business name is required.")
        @Size(min = 4, max = 30, message = "Business name must not exceed 30 characters.")
        String businessName,

        @NotBlank(message = "Contact is required.")
        @Size(min = 6, max = 20, message= "Contact must not exceed 20 characters.")
        String contact,

        @NotBlank(message = "Location is required.")
        @Size(min = 4, max = 40, message = "Location must not exceed 40 characters")
        String location

) {
}
