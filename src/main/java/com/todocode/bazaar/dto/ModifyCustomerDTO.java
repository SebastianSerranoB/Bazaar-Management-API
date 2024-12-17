package com.todocode.bazaar.dto;

import com.todocode.bazaar.models.enums.Status;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ModifyCustomerDTO(

        @NotNull
        @Positive
        Long id,

        @Size(max = 30, message = "Name must not exceed 30 characters.")
        String name,

        @Size(max = 30, message = "Name must not exceed 30 characters.")
        String surname,

        @Size(max = 20, message = "Gender must not exceed 20 characters.")
        String gender,

        @Past(message = "birthDate must be from a past time. Marty is that you?")
        LocalDate birthDate,

        @Size(max = 20, message = "NationalID must not exceed 20 characters.")
        String nationalId,

        @Size(max = 30, message = "Email must not exceed 30 characters.")
        String email,

        @Size(max = 20, message = "Phone must not exceed 20 characters.")
        String phone,

        @Size(max = 50, message = "Address must not exceed 50 characters.")
        String address,

        @Size(max = 20)
        String status

) {
}
