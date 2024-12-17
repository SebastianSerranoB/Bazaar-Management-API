package com.todocode.bazaar.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

public record CreateSupplyOrderDTO(

        @NotNull(message = "Supplier ID is required.")
        @Positive(message = "Supplier ID must be a positive number.")
        Long supplierId,

        @NotBlank(message = "Payment method is required.")
        @Pattern(regexp = "(?i)CASH|CREDIT_CARD|DEBIT_CARD|PAYPAL|BANK_TRANSFER", message = "Invalid Payment method. CASH|CREDIT_CARD|DEBIT_CARD|PAYPAL|BANK_TRANSFER")
        String paymentMethod,

        @NotEmpty(message = "Order details list is required.")
        @Valid
        List<CreateSupplyOrderDetailDTO> orderDetails
) {
}
