package com.todocode.bazaar.controllers;

import com.todocode.bazaar.dto.AnalyticsDTO.*;
import com.todocode.bazaar.services.IAnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class AnalyticsController {

    @Autowired
    IAnalyticsService analyticsService;



    @GetMapping("/analytics/mostSoldProduct")
    public ResponseEntity<List<ProductSalesSummaryDTO>> mostSoldProduct(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        return ResponseEntity.ok(analyticsService.getMostSoldProduct(startDate, endDate));
    }

    @GetMapping("/analytics/leastSoldProduct")
    public ResponseEntity<List<ProductSalesSummaryDTO>> leastSoldProduct(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        return ResponseEntity.ok(analyticsService.getLeastSoldProduct(startDate, endDate));
    }

    @GetMapping("/analytics/totalRevenue")
    public ResponseEntity<Double> totalRevenue(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        return ResponseEntity.ok(analyticsService.getTotalRevenue(startDate, endDate));
    }

    @GetMapping("/analytics/topCustomer")
    public ResponseEntity<List<CustomerPurchaseDTO>> topCustomer(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        return ResponseEntity.ok(analyticsService.getTopCustomer(startDate, endDate));
    }

    @GetMapping("/analytics/mostSoldCategory")
    public ResponseEntity<List<CategorySalesDTO>> mostSoldCategory(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        return ResponseEntity.ok(analyticsService.getMostSoldCategory(startDate, endDate));
    }

    @GetMapping("/analytics/mostUsedPaymentMethod")
    public ResponseEntity<List<PaymentMethodSalesDTO>> mostUsedPaymentMethod(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        return ResponseEntity.ok(analyticsService.getSalesByPaymentMethod(startDate, endDate));
    }

    @GetMapping("/analytics/averageSaleValue")
    public ResponseEntity<Double> averageSaleValue(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        return ResponseEntity.ok(analyticsService.getAverageSaleValue(startDate, endDate));
    }

    @GetMapping("/analytics/productsBelowStock/{threshold}")
    public ResponseEntity<List<ProductStockAlertDTO>> productsBelowStockThreshold(@PathVariable Integer threshold){
        return ResponseEntity.ok(analyticsService.getProductsBelowStock(threshold));
    }

    @GetMapping("/analytics/salesTrend")
    public ResponseEntity<List<SalesTrendDTO>> salesTrend(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
       return ResponseEntity.ok(analyticsService.getSalesTrend(startDate, endDate));
    }

    @GetMapping("/analytics/topProvider")
    public ResponseEntity<List<TopProviderDTO>> topProvider(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        return ResponseEntity.ok(analyticsService.getTopProviders(startDate, endDate));
    }









}
