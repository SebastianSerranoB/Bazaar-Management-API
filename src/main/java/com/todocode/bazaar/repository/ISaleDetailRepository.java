package com.todocode.bazaar.repository;

import com.todocode.bazaar.models.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISaleDetailRepository extends JpaRepository<SaleDetail, Long> {

    @Query("SELECT sd FROM SaleDetail sd WHERE sd.sale.id = :saleId")
    List<SaleDetail> findBySaleId(@Param("saleId") Long saleId);
}
