package com.todocode.bazaar.mapper;


import com.todocode.bazaar.dto.CreateSupplyOrderDetailDTO;
import com.todocode.bazaar.dto.SaleDetailResponseDTO;
import com.todocode.bazaar.dto.SupplyOrderDetailResponseDTO;
import com.todocode.bazaar.models.SaleDetail;
import com.todocode.bazaar.models.SupplyOrder;
import com.todocode.bazaar.models.SupplyOrderDetail;
import com.todocode.bazaar.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SupplyOrderDetailMapper {

    @Autowired
    IProductService productService;


    public SupplyOrderDetail toEntity(CreateSupplyOrderDetailDTO supplyOrderDetailDTO, SupplyOrder supplyOrder){
        SupplyOrderDetail supplyOrderDetail = new SupplyOrderDetail();

        supplyOrderDetail.setSupplyOrder(supplyOrder);
        supplyOrderDetail.setProduct(productService.getProductById(supplyOrderDetailDTO.productId()));
        supplyOrderDetail.setQuantity(supplyOrderDetailDTO.quantity());
        supplyOrderDetail.setUnitPrice(supplyOrderDetailDTO.unitPrice());

        return supplyOrderDetail;
    }


    public SupplyOrderDetailResponseDTO toResponseDTO(SupplyOrderDetail supplyOrderDetail) {
        return new SupplyOrderDetailResponseDTO(
                supplyOrderDetail.getId(),
                supplyOrderDetail.getProduct().getId(),
                supplyOrderDetail.getProduct().getName(),
                supplyOrderDetail.getProduct().getBrand(),
                supplyOrderDetail.getProduct().getCategory(),
                supplyOrderDetail.getQuantity(),
                supplyOrderDetail.getUnitPrice()
        );
    }


    public List<SupplyOrderDetailResponseDTO> SupplyOrderDetailListToResponseDTO(List<SupplyOrderDetail> supplyOrderDetails) {
        return supplyOrderDetails.stream()
                .map(this::toResponseDTO)
                .toList();
    }




}
