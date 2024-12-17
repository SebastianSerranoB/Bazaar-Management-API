package com.todocode.bazaar.repository;

import com.todocode.bazaar.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ISupplierRepository extends JpaRepository<Supplier, Long> {


    @Query("SELECT COUNT(s) > 0 FROM Supplier s WHERE s.businessName = :businessName")
    Boolean existsByBusinessName(@Param("businessName") String businessName);

    @Query("SELECT COUNT(s) > 0 FROM Supplier s WHERE s.contact = :contact")
    Boolean existsByContact(@Param("contact") String contact);

}
