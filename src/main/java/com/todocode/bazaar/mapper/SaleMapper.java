package com.todocode.bazaar.mapper;

import com.todocode.bazaar.dto.CreateSaleDTO;
import com.todocode.bazaar.dto.ModifySaleDTO;
import com.todocode.bazaar.dto.SaleDetailResponseDTO;
import com.todocode.bazaar.dto.SaleResponseDTO;
import com.todocode.bazaar.models.Customer;
import com.todocode.bazaar.models.Sale;
import com.todocode.bazaar.models.SaleDetail;
import com.todocode.bazaar.models.enums.PaymentMethod;
import com.todocode.bazaar.models.enums.Status;
import com.todocode.bazaar.repository.ISaleDetailRepository;
import com.todocode.bazaar.services.ICustomerService;
import com.todocode.bazaar.services.ISaleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SaleMapper {

     @Autowired
     ICustomerService customerService;

     @Autowired
     SaleDetailMapper saleDetailMapper;

     @Autowired
     ISaleDetailService saleDetailService;


    public Sale toEntity(CreateSaleDTO saleDTO){

        Sale sale = new Sale();
        sale.setCustomer(customerService.getCustomerById(saleDTO.customerId()));
        sale.setDate(LocalDate.now());
        sale.setPaymentMethod(PaymentMethod.valueOf(saleDTO.paymentMethod().toUpperCase()));
        sale.setStatus(Status.PENDING);

        return sale;
    }

    public SaleResponseDTO toResponseDTO(Sale sale){

        String customerFullName = sale.getCustomer().getName() + " " +  sale.getCustomer().getSurname();
        return new SaleResponseDTO(

                sale.getId(),
                sale.getDate(),
                sale.getTotalAmount(),
                sale.getPaymentMethod(),
                sale.getStatus(),
                sale.getCustomer().getId(),
                customerFullName,
                sale.getCustomer().getEmail(),
                mapSaleDetailToResponseDTO( saleDetailService.getDetailsFromSaleId(sale.getId()))
        );
    }

    private List<SaleDetailResponseDTO> mapSaleDetailToResponseDTO(List<SaleDetail> saleDetails) {
        return saleDetails.stream()
                .map(saleDetail -> saleDetailMapper.toResponseDTO(saleDetail))
                .toList();
    }



    public Sale updateEntity(Sale sale, ModifySaleDTO dto) {

        if (dto.paymentMethod() != null) {
            sale.setPaymentMethod(PaymentMethod.valueOf(dto.paymentMethod().toUpperCase()));
        }
        if (dto.status() != null) {
            sale.setStatus(Status.valueOf(dto.status().toUpperCase()));
        }

        return sale;
    }



}
