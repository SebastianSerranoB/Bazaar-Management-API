package com.todocode.bazaar.models;


import com.todocode.bazaar.models.enums.PaymentMethod;
import com.todocode.bazaar.models.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "supply_orders")
@Getter @Setter
public class SupplyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_supplier", nullable = false)
    private Supplier supplier;

    @NotNull
    @PastOrPresent
    private LocalDate orderDate;

    @Positive
    @Column(name = "total_supply_cost")
    private Double totalSupplyCost;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;


    public SupplyOrder(){

    }

    public SupplyOrder(Long id, Supplier supplier, LocalDate orderDate, Double totalSupplyCost, PaymentMethod paymentMethod, Status status) {
        this.id = id;
        this.supplier = supplier;
        this.orderDate = orderDate;
        this.totalSupplyCost = totalSupplyCost;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }




}
