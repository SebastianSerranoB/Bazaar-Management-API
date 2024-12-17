package com.todocode.bazaar.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;


@Table(name = "supply_order_details")
@Entity
@Getter @Setter
public class SupplyOrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_supply_order", nullable = false)
    private SupplyOrder supplyOrder;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    @Positive
    @NotNull
    private Double unitPrice;

    @Positive
    @NotNull
    private Integer quantity;

    public SupplyOrderDetail(){

    }

    public SupplyOrderDetail(Long id, SupplyOrder supplyOrder, Product product, Double unitPrice, Integer quantity) {
        this.id = id;
        this.supplyOrder = supplyOrder;
        this.product = product;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }
}
