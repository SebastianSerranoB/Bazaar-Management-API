package com.todocode.bazaar.repository;

import com.todocode.bazaar.dto.AnalyticsDTO.ProductStockAlertDTO;
import com.todocode.bazaar.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);


    @Query("SELECT COUNT(p) > 0 FROM Product p WHERE p.name = :name AND p.brand = :brand")
    boolean existsByNameAndBrand(@Param("name") String name, @Param("brand") String brand);


    @Query("SELECT new  com.todocode.bazaar.dto.AnalyticsDTO.ProductStockAlertDTO(p.id, p.name, p.category, p.stock) " +
            "FROM Product p " +
            "WHERE p.stock < :threshold")
    List<ProductStockAlertDTO> findProductsBelowStock(@Param("threshold") Integer threshold);

}
