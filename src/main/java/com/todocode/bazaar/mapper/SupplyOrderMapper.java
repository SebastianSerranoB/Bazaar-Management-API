package com.todocode.bazaar.mapper;


import com.todocode.bazaar.dto.*;
import com.todocode.bazaar.models.SaleDetail;
import com.todocode.bazaar.models.SupplyOrder;
import com.todocode.bazaar.models.SupplyOrderDetail;
import com.todocode.bazaar.models.enums.PaymentMethod;
import com.todocode.bazaar.models.enums.Status;
import com.todocode.bazaar.repository.ISupplyOrderDetailRepository;
import com.todocode.bazaar.services.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class SupplyOrderMapper {

    @Autowired
    ISupplierService supplierService;

    @Autowired
    SupplyOrderDetailMapper supplyOrderDetailMapper;

    @Autowired
    ISupplyOrderDetailRepository supplyOrderDetailRepository;



    public SupplyOrder toEntity(CreateSupplyOrderDTO supplyOrderDTO){
        SupplyOrder supplyOrderAux = new SupplyOrder();

        supplyOrderAux.setPaymentMethod(PaymentMethod.valueOf(supplyOrderDTO.paymentMethod().toUpperCase()));
        supplyOrderAux.setSupplier(supplierService.getSupplierById(supplyOrderDTO.supplierId()));
        supplyOrderAux.setOrderDate(LocalDate.now());
        supplyOrderAux.setStatus(Status.PENDING);

        return supplyOrderAux;
    }


    public SupplyOrder update(SupplyOrder supplyOrder, ModifySupplyOrderDTO supplyOrderDTO){

        if (supplyOrderDTO.paymentMethod() != null) {
            supplyOrder.setPaymentMethod(PaymentMethod.valueOf(supplyOrderDTO.paymentMethod().toUpperCase()));
        }
        if (supplyOrderDTO.status() != null) {
            supplyOrder.setStatus(Status.valueOf(supplyOrderDTO.status().toUpperCase()));
        }

        return supplyOrder;
    }


    public SupplyOrderResponseDTO toDTO(SupplyOrder supplyOrder){
        return new SupplyOrderResponseDTO(
                supplyOrder.getId(),
                supplyOrder.getOrderDate(),
                supplyOrder.getTotalSupplyCost(),
                supplyOrder.getPaymentMethod(),
                supplyOrder.getStatus(),
                supplyOrder.getSupplier().getId(),
                supplyOrder.getSupplier().getBusinessName(),
                supplyOrderDetailMapper.SupplyOrderDetailListToResponseDTO
                                                    (supplyOrderDetailRepository.findListBySupplyId(supplyOrder.getId()))
        );
    }



}
