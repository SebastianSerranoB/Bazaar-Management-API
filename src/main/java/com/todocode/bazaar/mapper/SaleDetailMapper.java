package com.todocode.bazaar.mapper;

import com.todocode.bazaar.dto.CreateSaleDetailDTO;
import com.todocode.bazaar.dto.SaleDetailResponseDTO;
import com.todocode.bazaar.models.Product;
import com.todocode.bazaar.models.Sale;
import com.todocode.bazaar.models.SaleDetail;
import com.todocode.bazaar.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaleDetailMapper {

    @Autowired
    IProductService productService;

    public SaleDetail toEntity(CreateSaleDetailDTO saleDetailDTO, Sale sale){

        SaleDetail saleDetail = new SaleDetail();
        Product productAux = productService.getProductById(saleDetailDTO.productId());

        saleDetail.setSale(sale);
        saleDetail.setProduct(productAux);
        saleDetail.setProductQuantity(saleDetailDTO.productQuantity());
        saleDetail.setSalePrice(productAux.getSalePrice());

        return saleDetail;
    }


    public SaleDetailResponseDTO toResponseDTO(SaleDetail saleDetail) {
        return new SaleDetailResponseDTO(
                saleDetail.getId(),
                saleDetail.getProduct().getId(),  // Assuming each SaleDetail has a Product
                saleDetail.getProduct().getName(),
                saleDetail.getProduct().getBrand(),
                saleDetail.getProduct().getCategory(),
                saleDetail.getProductQuantity(),
                saleDetail.getSalePrice()
        );
    }


}
