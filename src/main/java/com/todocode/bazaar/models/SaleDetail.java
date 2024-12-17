package com.todocode.bazaar.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sale_details")
@Getter @Setter
public class SaleDetail {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_sale", nullable = false)
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    @Positive
    @Column(name = "product_quantity", nullable = false)
    private Integer productQuantity;

    @NotNull
    @Positive
    @Column(name = "sale_price")
    private Double salePrice;


    public SaleDetail(){

    }

    public SaleDetail(Long id, Sale sale, Product product, Integer productQuantity, Double salePrice) {
        this.id = id;
        this.sale = sale;
        this.product = product;
        this.productQuantity = productQuantity;
        this.salePrice = salePrice;
    }

}
