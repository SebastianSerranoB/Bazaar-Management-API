package com.todocode.bazaar.repository;

import com.todocode.bazaar.models.SupplyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISupplyOrderRepository extends JpaRepository<SupplyOrder, Long> {


}
