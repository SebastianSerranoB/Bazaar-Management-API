package com.todocode.bazaar.dto;

import com.todocode.bazaar.models.enums.ProductCategory;

public record SaleDetailResponseDTO(

         Long id,
         Long productId,
         String productName,
         String productBrand,
         ProductCategory productCategory,
         Integer productQuantity,
         Double salePrice

) {
}
