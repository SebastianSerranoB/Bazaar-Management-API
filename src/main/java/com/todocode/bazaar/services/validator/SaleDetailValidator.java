package com.todocode.bazaar.services.validator;

import com.todocode.bazaar.dto.CreateSaleDTO;
import com.todocode.bazaar.dto.CreateSaleDetailDTO;
import com.todocode.bazaar.exceptions.BusinessException;
import com.todocode.bazaar.exceptions.NotFoundException;
import com.todocode.bazaar.models.Product;
import com.todocode.bazaar.models.enums.Status;
import com.todocode.bazaar.services.ICustomerService;
import com.todocode.bazaar.services.IProductService;
import com.todocode.bazaar.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SaleDetailValidator {

    @Autowired
    IProductService productService;


    public void validateCreateSaleDetail(List<CreateSaleDetailDTO> saleDetailDTO){

        for(CreateSaleDetailDTO s :saleDetailDTO){
            validateProductSale(s.productId(), s.productQuantity());
        }

    }


    public void validateProductSale(Long id, Integer productQuantity){

        Product auxProduct = productService.getProductById(id);

        if(auxProduct != null){

            if(auxProduct.getStatus() != Status.ACTIVE){
                throw new BusinessException("Product status must be ACTIVE.");
            }

            if(!productService.checkStock(id, productQuantity)){
                throw new BusinessException("there isn't enough stock available for the required quantity. STOCK AVAILABLE: "
                                                    + auxProduct.getStock() + " QUANTITY REQUIRED: " + productQuantity);
            }


        }else{
            throw new NotFoundException("Product not found. ID: " + id);
        }

    }



}
