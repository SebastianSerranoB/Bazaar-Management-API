package com.todocode.bazaar.models;


import com.todocode.bazaar.models.enums.PaymentMethod;
import com.todocode.bazaar.models.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "sales")
@Getter @Setter
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @PastOrPresent
    private LocalDate date;


    @Positive
    @Column(name = "total_amount", nullable = true)
    private Double totalAmount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "id_customer", nullable = false)
    private Customer customer;


    public Sale(){

    }


    public Sale(Long id, LocalDate date, Double totalAmount, PaymentMethod paymentMethod, Status status, Customer customer) {
        this.id = id;
        this.date = date;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.customer = customer;
    }

}
