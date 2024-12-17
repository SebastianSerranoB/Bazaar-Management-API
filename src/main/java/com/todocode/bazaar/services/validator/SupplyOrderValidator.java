package com.todocode.bazaar.services.validator;

import com.todocode.bazaar.dto.CreateSupplyOrderDTO;
import com.todocode.bazaar.dto.CreateSupplyOrderDetailDTO;
import com.todocode.bazaar.dto.ModifySupplyOrderDTO;
import com.todocode.bazaar.exceptions.BusinessException;
import com.todocode.bazaar.exceptions.NotFoundException;
import com.todocode.bazaar.models.*;
import com.todocode.bazaar.models.enums.Status;
import com.todocode.bazaar.repository.ISupplyOrderRepository;
import com.todocode.bazaar.services.IProductService;
import com.todocode.bazaar.services.ISupplierService;
import com.todocode.bazaar.services.ISupplyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupplyOrderValidator {


    @Autowired
    ISupplyOrderRepository supplyOrderRepository;

    @Autowired
    ISupplierService supplierService;

    @Autowired
    IProductService productService;


    public void validateCreateSupplyOrder(CreateSupplyOrderDTO supplyOrderDTO){

        this.validateSupplierForOrder(supplyOrderDTO);
        this.validateSupplyOrderDetails(supplyOrderDTO);
    }



    private void validateSupplierForOrder(CreateSupplyOrderDTO supplyOrderDTO) {
        Supplier supplierAux = supplierService.getSupplierById(supplyOrderDTO.supplierId());
        if(supplierAux != null){
            if(supplierAux.getStatus() != Status.ACTIVE){
                throw new BusinessException("Supplier STATUS must be ACTIVE. Current status: " + supplierAux.getStatus());
            }

        }else{
            throw new NotFoundException("Supplier not found. ID: " + supplyOrderDTO.supplierId());
        }
    }


    private void validateSupplyOrderDetails(CreateSupplyOrderDTO supplyOrderDTO) {
        for(CreateSupplyOrderDetailDTO s : supplyOrderDTO.orderDetails()){

            Product productAux = productService.getProductById(s.productId());
            if(productAux != null){
                if(productAux.getStatus() != Status.ACTIVE){
                    throw new BusinessException("Product STATUS must be ACTIVE. Current status: " + productAux.getStatus());
                }

            }else{
                throw new NotFoundException("Product not found. ID: " + s.productId());
            }
        }
    }


    public void validateUpdateSupplyOrder(ModifySupplyOrderDTO supplyOrderDTO){
        SupplyOrder supplyOrderAux = supplyOrderRepository
                                                        .findById(supplyOrderDTO.id())
                                                        .orElseThrow( () -> new NotFoundException("Supply order not found. ID: " + supplyOrderDTO.id()));
    }


}
