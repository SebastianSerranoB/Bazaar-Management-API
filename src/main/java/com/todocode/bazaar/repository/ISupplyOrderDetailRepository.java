package com.todocode.bazaar.repository;

import com.todocode.bazaar.dto.AnalyticsDTO.TopProviderDTO;
import com.todocode.bazaar.models.SupplyOrderDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ISupplyOrderDetailRepository extends JpaRepository<SupplyOrderDetail, Long> {

    @Query("SELECT sd FROM SupplyOrderDetail sd WHERE sd.supplyOrder.id = :supplyId")
    List<SupplyOrderDetail> findListBySupplyId(@Param("supplyId") Long supplyId);



    @Query("SELECT new com.todocode.bazaar.dto.AnalyticsDTO.TopProviderDTO(s.id, s.businessName, SUM(sod.quantity))" +
            "FROM SupplyOrderDetail sod " +
            "JOIN sod.supplyOrder so " +
            "JOIN so.supplier s " +
            "WHERE so.orderDate BETWEEN :startDate AND :endDate " +
            "GROUP BY s.id, s.businessName " +
            "ORDER BY SUM(sod.quantity) DESC")
    List<TopProviderDTO> findTopProvider(@Param("startDate") LocalDate startDate,
                                         @Param("endDate") LocalDate endDate, Pageable pageable);



}
