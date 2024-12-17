package com.todocode.bazaar.repository;

import com.todocode.bazaar.dto.AnalyticsDTO.*;
import com.todocode.bazaar.models.Sale;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, Long> {


    @Query("SELECT new com.todocode.bazaar.dto.AnalyticsDTO.ProductSalesSummaryDTO(p.id, p.name, p.category, SUM(sd.productQuantity)) " +
            "FROM SaleDetail sd " +
            "JOIN sd.product p " +
            "WHERE sd.sale.date BETWEEN :startDate AND :endDate " +
            "GROUP BY p.id, p.name, p.category " +
            "ORDER BY SUM(sd.productQuantity) DESC")
    List<ProductSalesSummaryDTO> findMostSoldProduct(@Param("startDate") LocalDate startDate,
                                                     @Param("endDate") LocalDate endDate, Pageable pageable);


    @Query("SELECT new com.todocode.bazaar.dto.AnalyticsDTO.ProductSalesSummaryDTO(p.id, p.name, p.category, SUM(sd.productQuantity)) " +
            "FROM SaleDetail sd " +
            "JOIN sd.product p " +
            "WHERE sd.sale.date BETWEEN :startDate AND :endDate " +
            "GROUP BY p.id, p.name, p.category " +
            "ORDER BY SUM(sd.productQuantity)")
    List<ProductSalesSummaryDTO> findLeastSellProduct(@Param("startDate") LocalDate startDate,
                                            @Param("endDate") LocalDate endDate, Pageable pageable);

    @Query("SELECT SUM(s.totalAmount) " +
            "FROM Sale s " +
            "WHERE s.date BETWEEN :startDate AND :endDate AND s.status = 'COMPLETED'")
    Double getTotalRevenue(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);


    @Query("SELECT new com.todocode.bazaar.dto.AnalyticsDTO.CustomerPurchaseDTO(c.id, c.surname, SUM(s.totalAmount)) " +
            "FROM Sale s " +
            "JOIN s.customer c " +
            "WHERE s.date BETWEEN :startDate AND :endDate AND s.status = 'COMPLETED' " +
            "GROUP BY c.id, c.surname " +
            "ORDER BY SUM(s.totalAmount) DESC")
    List<CustomerPurchaseDTO> findTopCustomer(@Param("startDate") LocalDate startDate,
                                        @Param("endDate") LocalDate endDate, Pageable pageable);


    @Query("SELECT new com.todocode.bazaar.dto.AnalyticsDTO.CategorySalesDTO(p.category, SUM(sd.productQuantity)) " +
            "FROM SaleDetail sd " +
            "JOIN sd.product p " +
            "WHERE sd.sale.date BETWEEN :startDate AND :endDate " +
            "GROUP BY p.category " +
            "ORDER BY SUM(sd.productQuantity)")
    List<CategorySalesDTO> findMostSoldCategory(@Param("startDate") LocalDate startDate,
                                                @Param("endDate") LocalDate endDate, Pageable pageable);



    @Query("SELECT new com.todocode.bazaar.dto.AnalyticsDTO.PaymentMethodSalesDTO(s.paymentMethod, COUNT(s)) " +
            "FROM Sale s " +
            "WHERE s.date BETWEEN :startDate AND :endDate AND s.status = 'COMPLETED' " +
            "GROUP BY s.paymentMethod")
    List<PaymentMethodSalesDTO> findSalesByPaymentMethod(@Param("startDate") LocalDate startDate,
                                                           @Param("endDate") LocalDate endDate);


    @Query("SELECT AVG(s.totalAmount) " +
            "FROM Sale s " +
            "WHERE s.date BETWEEN :startDate AND :endDate AND s.status = 'COMPLETED'")
    Double findAverageSaleValue(@Param("startDate") LocalDate startDate,
                                @Param("endDate") LocalDate endDate);



    @Query("SELECT new com.todocode.bazaar.dto.AnalyticsDTO.SalesTrendDTO(s.date, SUM(s.totalAmount)) " +
            "FROM Sale s " +
            "WHERE s.date BETWEEN :startDate AND :endDate AND s.status = 'COMPLETED' " +
            "GROUP BY s.date " +
            "ORDER BY s.date ASC"

    )
    List<SalesTrendDTO> findSalesTrend(@Param("startDate") LocalDate startDate,
                                       @Param("endDate") LocalDate endDate, Pageable pageable);













}
