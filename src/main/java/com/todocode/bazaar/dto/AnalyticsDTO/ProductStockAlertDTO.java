package com.todocode.bazaar.dto.AnalyticsDTO;

import com.todocode.bazaar.models.enums.ProductCategory;

public record ProductStockAlertDTO(Long productId, String productName, ProductCategory productCategory, Integer stock) {
}
