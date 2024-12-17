package com.todocode.bazaar.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;


public record CreateSaleDTO(

        @NotBlank(message = "Payment method is required.")
        @Pattern(regexp = "(?i)CASH|CREDIT_CARD|DEBIT_CARD|PAYPAL|BANK_TRANSFER", message = "Invalid category. CASH|CREDIT_CARD|DEBIT_CARD|PAYPAL|BANK_TRANSFER")
        String paymentMethod,

        @NotNull(message = "Customer ID is required.")
        @Positive(message = "Customer ID must be a positive number.")
         Long customerId,

        @NotEmpty(message = "Sale details are required.")
        @Valid
        List<CreateSaleDetailDTO> saleDetails

) {
}
