package com.todocode.bazaar.services;

import com.todocode.bazaar.dto.CreateProductDTO;
import com.todocode.bazaar.dto.ModifyProductDTO;
import com.todocode.bazaar.dto.ProductResponseDTO;
import com.todocode.bazaar.models.Product;

import java.util.List;

public interface IProductService {

    public List<Product> getAllProducts();

    public List<ProductResponseDTO> getAllProductsResponse();

    public void createProduct(CreateProductDTO productDTO);

    public Product getProductById(Long id);

    public ProductResponseDTO getProductByIdResponse(Long id);

    public void updateProduct(ModifyProductDTO productDTO);

    public void deleteProduct(Long id);

    public Boolean checkStock(Long id, Integer stockRequired);

    public void deductStock(Long productId, Integer quantity);

    public void replenishStock(Long productId, Integer quantity);
}
