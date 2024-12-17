package com.todocode.bazaar.dto.AnalyticsDTO;

import com.todocode.bazaar.models.enums.PaymentMethod;

public record PaymentMethodSalesDTO(PaymentMethod paymentMethod, Long totalSales) {}

