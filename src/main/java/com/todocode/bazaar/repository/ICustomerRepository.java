package com.todocode.bazaar.repository;

import com.todocode.bazaar.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT COUNT(c) > 0 FROM Customer c WHERE c.nationalId = :national_id")
    boolean existByNationalId(@Param("national_id") String nationalId);

    @Query("SELECT COUNT(c) > 0 FROM Customer c WHERE c.phone = :phone")
    boolean existByPhone(@Param("phone") String phone) ;

    @Query("SELECT COUNT(c) > 0 FROM Customer c WHERE c.email = :email")
    boolean existByEmail(@Param("email") String email) ;

}
