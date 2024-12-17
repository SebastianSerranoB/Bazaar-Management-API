package com.todocode.bazaar.models;

import com.todocode.bazaar.models.enums.ProductCategory;
import com.todocode.bazaar.models.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter @Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @NotBlank
    @Size(min = 3, max = 20)
    private String brand;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @NotNull
    @Positive
    @Column(name = "sale_price")
    private Double salePrice;

    @NotNull
    @PositiveOrZero
    private Integer stock;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;


    public Product()
    {

    }

    public Product(Long id, String name, String brand, ProductCategory category, Double salePrice, Integer stock, Status status) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.salePrice = salePrice;
        this.stock = stock;
        this.status = status;
    }

}
