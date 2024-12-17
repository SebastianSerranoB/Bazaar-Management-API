package com.todocode.bazaar.mapper;

import com.todocode.bazaar.dto.CreateProductDTO;
import com.todocode.bazaar.dto.ModifyProductDTO;
import com.todocode.bazaar.dto.ProductResponseDTO;
import com.todocode.bazaar.models.Product;
import com.todocode.bazaar.models.enums.ProductCategory;
import com.todocode.bazaar.models.enums.Status;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(CreateProductDTO dto) {
        Product product = new Product();
        product.setName(dto.name());
        product.setBrand(dto.brand());
        product.setCategory(ProductCategory.valueOf(dto.category().toUpperCase() ) );
        product.setSalePrice(dto.salePrice());
        product.setStock(dto.stock());
        product.setStatus(Status.ACTIVE);
        return product;
    }

    public Product updateEntity(Product product, ModifyProductDTO dto) {
        if (dto.name() != null) product.setName(dto.name());
        if (dto.brand() != null) product.setBrand(dto.brand());
        if (dto.category() != null) product.setCategory(ProductCategory.valueOf(dto.category().toUpperCase()));
        if (dto.salePrice() != null) product.setSalePrice(dto.salePrice());
        if (dto.stock() != null) product.setStock(dto.stock());
        if (dto.status() != null) product.setStatus(Status.valueOf(dto.status().toUpperCase()));

        return product;
    }

    public ProductResponseDTO toDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getBrand(),
                product.getCategory(),
                product.getSalePrice(),
                product.getStock(),
                product.getStatus()
        );
    }
}


