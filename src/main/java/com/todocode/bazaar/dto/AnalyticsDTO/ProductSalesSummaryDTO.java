package com.todocode.bazaar.dto.AnalyticsDTO;

import com.todocode.bazaar.models.enums.ProductCategory;


public record ProductSalesSummaryDTO(Long productId,
                                 String productName,
                                 ProductCategory productCategory,
                                 Long totalQuantitySold) {
}
