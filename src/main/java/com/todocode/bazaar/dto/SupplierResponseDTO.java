package com.todocode.bazaar.dto;

import com.todocode.bazaar.models.enums.Status;

public record SupplierResponseDTO(Long id, String businessName, String location, String contact, Status status) {
}
