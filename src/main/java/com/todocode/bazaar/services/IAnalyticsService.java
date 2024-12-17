package com.todocode.bazaar.services;


import com.todocode.bazaar.dto.AnalyticsDTO.*;


import java.time.LocalDate;
import java.util.List;

public interface IAnalyticsService {

    public List<ProductSalesSummaryDTO> getMostSoldProduct(LocalDate startDate, LocalDate endDate);

    public List<ProductSalesSummaryDTO> getLeastSoldProduct(LocalDate startDate, LocalDate endDate);

    public Double getTotalRevenue(LocalDate startDate, LocalDate endDate);

    public List<CustomerPurchaseDTO> getTopCustomer(LocalDate startDate, LocalDate endDate);

    public List<CategorySalesDTO> getMostSoldCategory(LocalDate startDate, LocalDate endDate);

    public List<PaymentMethodSalesDTO> getSalesByPaymentMethod(LocalDate startDate, LocalDate endDate);

    public Double getAverageSaleValue(LocalDate startDate, LocalDate endDate);

    public List<ProductStockAlertDTO> getProductsBelowStock(Integer threshold);

    public List<SalesTrendDTO> getSalesTrend(LocalDate startDate, LocalDate endDate);

    public List<TopProviderDTO> getTopProviders(LocalDate startDate, LocalDate endDate);
}
