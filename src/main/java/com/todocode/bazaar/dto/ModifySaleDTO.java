package com.todocode.bazaar.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record ModifySaleDTO(

        @NotNull(message = "ID is required.")
        @Positive(message = "ID must be a positive number.")
        Long id,

        @Pattern(regexp = "(?i)CASH|CREDIT_CARD|DEBIT_CARD|PAYPAL|BANK_TRANSFER", message = "Invalid Payment method. CASH|CREDIT_CARD|DEBIT_CARD|PAYPAL|BANK_TRANSFER")
        String paymentMethod,

        @Pattern(regexp = "(?i)COMPLETED|CANCELED", message = "Invalid status. COMPLETED|CANCELED")
        String status

) {
}
