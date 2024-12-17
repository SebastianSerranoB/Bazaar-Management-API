package com.todocode.bazaar.services;

import com.todocode.bazaar.dto.CreateProductDTO;
import com.todocode.bazaar.dto.ModifyProductDTO;
import com.todocode.bazaar.dto.ProductResponseDTO;
import com.todocode.bazaar.exceptions.NotFoundException;
import com.todocode.bazaar.mapper.ProductMapper;
import com.todocode.bazaar.models.Product;
import com.todocode.bazaar.models.enums.Status;
import com.todocode.bazaar.repository.IProductRepository;
import com.todocode.bazaar.services.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService{


    @Autowired
    private IProductRepository productRepo;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductValidator productValidator;


    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public List<ProductResponseDTO> getAllProductsResponse() {
        return  productRepo.findAll().stream()
                .map(product -> productMapper.toDTO(product))
                .collect(Collectors.toList());
    }

    @Override
    public void createProduct(CreateProductDTO productDTO) {
        productValidator.validateCreateProduct(productDTO);
        productRepo.save(productMapper.toEntity(productDTO));
    }

    @Override
    public Product getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public ProductResponseDTO getProductByIdResponse(Long id) {
        return productMapper.toDTO(productRepo.findById(id).
                                                orElseThrow( () -> new NotFoundException("Product not found. ID: " + id)));
    }


    @Override
    public void updateProduct(ModifyProductDTO productDTO) {

        productValidator.validateUpdateProduct(productDTO);
        productRepo.save(productMapper.updateEntity
                                                (this.getProductById(productDTO.id()), productDTO));
    }

    @Override
    public void deleteProduct(Long id) {
        Product auxProduct = productRepo.findById(id).
                     orElseThrow( () -> new NotFoundException("Product not found. ID: " + id));

        auxProduct.setStatus(Status.DELETED);
        productRepo.save(auxProduct);
    }

    @Override
    public Boolean checkStock(Long id, Integer stockRequired){
       return this.getProductById(id).getStock() >= stockRequired;
    }

    @Override
    public void deductStock(Long productId, Integer quantity) {
        Product productAux = this.getProductById(productId);
        productAux.setStock(productAux.getStock() - quantity);
        productRepo.save(productAux);
    }

    @Override
    public void replenishStock(Long productId, Integer quantity) {
        Product productAux = this.getProductById(productId);
        productAux.setStock(productAux.getStock() + quantity);
        productRepo.save(productAux);
    }


}
