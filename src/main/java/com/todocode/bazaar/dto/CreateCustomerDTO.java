package com.todocode.bazaar.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CreateCustomerDTO(


        @NotBlank(message = "Name is required.")
        @Size(max = 30, message = "Name must not exceed 30 characters.")
        String name,

        @NotBlank(message = "Surname is required.")
        @Size(max = 30, message = "Name must not exceed 30 characters.")
        String surname,

        @NotBlank(message = "Gender is required.")
        @Size(max = 20, message = "Gender must not exceed 20 characters.")
        String gender,

        @NotNull(message = "birthDate is required")
        @Past(message = "birthDate must be from a past time. Marty is that you?")
        LocalDate birthDate,

        @NotBlank(message = "NationalID is required.")
        @Size(max = 20, message = "NationalID must not exceed 20 characters.")
        String nationalId,

        @NotBlank(message = "Email is required.")
        @Size(max = 30, message = "Email must not exceed 30 characters.")
        String email,

        @NotBlank(message = "Phone is required.")
        @Size(max = 20, message = "Phone must not exceed 20 characters.")
        String phone,

        @NotBlank(message = "Address is required.")
        @Size(max = 50, message = "Address must not exceed 50 characters.")
        String address

) {
}
