package com.todocode.bazaar.models;


import com.todocode.bazaar.models.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "suppliers")
@Getter @Setter
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Column(name = "business_name")
    private String businessName;

    @NotBlank
    private String contact;

    @NotBlank
    private String location;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;


    public Supplier(){

    }

    public Supplier(Long id, String businessName, String contact, String location, Status status) {
        this.id = id;
        this.businessName = businessName;
        this.contact = contact;
        this.location = location;
        this.status = status;
    }
}
