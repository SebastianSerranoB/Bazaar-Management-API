package com.todocode.bazaar.models;


import com.todocode.bazaar.models.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "customers")
@Getter @Setter
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @NotBlank
    @Size(min = 3, max = 20)
    private String surname;

    @NotBlank
    @Size(min = 3, max = 15)
    private String gender;

    @NotNull
    @Past
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NotBlank
    @Size(min = 7, max = 15)
    @Column(name = "national_id")
    private String nationalId;

    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 20)
    private String phone;

    @NotBlank
    @Size(min = 5, max = 50)
    private String address;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    public Customer() {

    }

    public Customer(Long id, String name, String surname, String gender, LocalDate birthDate, String nationalId, String email, String phone, String address, Status status) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthDate = birthDate;
        this.nationalId = nationalId;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.status = status;
    }


}
