package com.todocode.bazaar.dto;

import com.todocode.bazaar.models.enums.Status;

import java.time.LocalDate;

public record CustomerResponseDTO(Long id, String name, String surname, String gender,
                                  LocalDate birthDate, String nationalID, String email,
                                  String phone, String address, Status status) {
}
