package com.todocode.bazaar.dto.AnalyticsDTO;

import com.todocode.bazaar.models.enums.ProductCategory;

public record CategorySalesDTO(ProductCategory category, Long totalQuantitySold) {}