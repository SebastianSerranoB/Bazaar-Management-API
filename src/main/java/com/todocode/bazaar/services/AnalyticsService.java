package com.todocode.bazaar.services;


import com.todocode.bazaar.dto.AnalyticsDTO.*;
import com.todocode.bazaar.exceptions.BusinessException;
import com.todocode.bazaar.repository.IProductRepository;
import com.todocode.bazaar.repository.ISaleRepository;
import com.todocode.bazaar.repository.ISupplyOrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AnalyticsService implements IAnalyticsService{

    @Autowired
    IProductRepository productRepository;

    @Autowired
    ISaleRepository saleRepository;

    @Autowired
    ISupplyOrderDetailRepository supplyOrderDetailRepository;

    private static final Pageable TOP_ONE_PAGEABLE = PageRequest.of(0, 1);
    private static final Pageable TOP_THREE_PAGEABLE = PageRequest.of(0, 3);


    @Override
    public List<ProductSalesSummaryDTO> getMostSoldProduct(LocalDate startDate, LocalDate endDate) {
        List<ProductSalesSummaryDTO> results = saleRepository.findMostSoldProduct(startDate, endDate, TOP_ONE_PAGEABLE);

        if(!results.isEmpty()) {
            return results;
        }else{
            throw new BusinessException("No products sold between the time period.");
        }
    }

    @Override
    public List<ProductSalesSummaryDTO> getLeastSoldProduct(LocalDate startDate, LocalDate endDate) {
        List<ProductSalesSummaryDTO> results =  saleRepository.findLeastSellProduct(startDate, endDate, TOP_THREE_PAGEABLE);

        if(!results.isEmpty()) {
            return results;
        }else{
            throw new BusinessException("No products sold between the time period.");
        }
    }

    @Override
    public Double getTotalRevenue(LocalDate startDate, LocalDate endDate) {
        Double revenue =  saleRepository.getTotalRevenue(startDate, endDate);

        if(revenue != null){
            return revenue;
        }else{
            throw new BusinessException("No sells registered between the time period.");
        }
    }

    @Override
    public List<CustomerPurchaseDTO> getTopCustomer(LocalDate startDate, LocalDate endDate) {
        List<CustomerPurchaseDTO> results =  saleRepository.findTopCustomer(startDate, endDate, TOP_THREE_PAGEABLE);

        if(!results.isEmpty()) {
            return results;
        }else{
            throw new BusinessException("No products sold between the time period.");
        }
    }

    @Override
    public List<CategorySalesDTO> getMostSoldCategory(LocalDate startDate, LocalDate endDate) {
        List<CategorySalesDTO> results =  saleRepository.findMostSoldCategory(startDate, endDate, TOP_THREE_PAGEABLE);

        if(!results.isEmpty()) {
            return results;
        }else{
            throw new BusinessException("No products sold between the time period.");
        }
    }

    @Override
    public List<PaymentMethodSalesDTO> getSalesByPaymentMethod(LocalDate startDate, LocalDate endDate) {
        return  saleRepository.findSalesByPaymentMethod(startDate, endDate);
    }

    @Override
    public Double getAverageSaleValue(LocalDate startDate, LocalDate endDate) {
        Double value = saleRepository.findAverageSaleValue(startDate, endDate);

        if(value != null){
            return value;
        }else{
            throw new BusinessException("No products sold between the time period.");
        }
    }


    @Override
    public List<ProductStockAlertDTO> getProductsBelowStock(Integer threshold) {
        List<ProductStockAlertDTO> results =  productRepository.findProductsBelowStock(threshold);

        if(!results.isEmpty()) {
            return results;
        }else{
            throw new BusinessException("No products sold between the time period.");
        }
    }

    @Override
    public List<SalesTrendDTO> getSalesTrend(LocalDate startDate, LocalDate endDate) {
        List<SalesTrendDTO> results =  saleRepository.findSalesTrend(startDate, endDate, PageRequest.of(0, 20));

        if(!results.isEmpty()) {
            return results;
        }else{
            throw new BusinessException("No products sold between the time period.");
        }
    }

    @Override
    public List<TopProviderDTO> getTopProviders(LocalDate startDate, LocalDate endDate) {
        List<TopProviderDTO> results =  supplyOrderDetailRepository.findTopProvider(startDate, endDate, PageRequest.of(0, 5));

        if(!results.isEmpty()) {
            return results;
        }else{
            throw new BusinessException("No orders registered between the time period.");
        }
    }


}
