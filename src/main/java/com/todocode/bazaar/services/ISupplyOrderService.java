package com.todocode.bazaar.services;

import com.todocode.bazaar.dto.CreateSupplyOrderDTO;
import com.todocode.bazaar.dto.ModifySupplierDTO;
import com.todocode.bazaar.dto.ModifySupplyOrderDTO;
import com.todocode.bazaar.dto.SupplyOrderResponseDTO;
import com.todocode.bazaar.models.SupplyOrder;

import java.time.LocalDate;
import java.util.List;

public interface ISupplyOrderService {

    public List<SupplyOrderResponseDTO> getAllSupplies();

    public SupplyOrderResponseDTO findByIdResponse(Long id);

    public SupplyOrder findById(Long id);

    public void createSupply(CreateSupplyOrderDTO supplyDTO);

    public void updateSupply(ModifySupplyOrderDTO supplyDTO);

    public void deleteSupply(Long id);

}
