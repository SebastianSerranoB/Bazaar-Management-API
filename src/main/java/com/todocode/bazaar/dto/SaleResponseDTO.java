package com.todocode.bazaar.dto;

import com.todocode.bazaar.models.enums.PaymentMethod;
import com.todocode.bazaar.models.enums.Status;

import java.time.LocalDate;
import java.util.List;

public record SaleResponseDTO(

        Long id,
        LocalDate date,
        Double totalAmount,
        PaymentMethod paymentMethod,
        Status status,
        Long customerId,
        String customerFullName,
        String customerEmail,
        List<SaleDetailResponseDTO> saleDetails

) {
}
