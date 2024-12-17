package com.todocode.bazaar.services.validator;


import com.todocode.bazaar.dto.CreateSaleDTO;
import com.todocode.bazaar.dto.ModifySaleDTO;
import com.todocode.bazaar.dto.ModifySupplierDTO;
import com.todocode.bazaar.exceptions.BusinessException;
import com.todocode.bazaar.exceptions.NotFoundException;
import com.todocode.bazaar.models.Customer;
import com.todocode.bazaar.models.Sale;
import com.todocode.bazaar.models.Supplier;
import com.todocode.bazaar.models.enums.Status;
import com.todocode.bazaar.repository.ISaleRepository;
import com.todocode.bazaar.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaleValidator {

    @Autowired
    ICustomerService customerService;

    @Autowired
    SaleDetailValidator saleDetailValidator;

    @Autowired
    ISaleRepository saleRepository;


    public void validateCreateSale(CreateSaleDTO saleDTO){

           Customer customerAux =  customerService.getCustomerById(saleDTO.customerId());
           if(customerAux != null){

               if(customerAux.getStatus() != Status.ACTIVE){
                   throw new BusinessException("Customer status must be ACTIVE.");
               }

               saleDetailValidator.validateCreateSaleDetail(saleDTO.saleDetails());

           }else{
               throw new NotFoundException("Customer not found. ID: " +  saleDTO.customerId());
           }

    }


    public void validateUpdateSale(ModifySaleDTO saleDTO){

        Sale saleAux = saleRepository.findById(saleDTO.id())
                                            .orElseThrow( () -> new NotFoundException("Sale not found. ID: " + saleDTO.id()));
    }


}
