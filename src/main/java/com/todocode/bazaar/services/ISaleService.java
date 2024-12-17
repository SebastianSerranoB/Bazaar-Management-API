package com.todocode.bazaar.services;

import com.todocode.bazaar.dto.CreateSaleDTO;
import com.todocode.bazaar.dto.ModifySaleDTO;
import com.todocode.bazaar.dto.SaleResponseDTO;
import com.todocode.bazaar.models.Sale;

import java.util.List;

public interface ISaleService {

    public List<SaleResponseDTO> getAllSales();

    public void createSale(CreateSaleDTO saleDTO);

    public Sale findSaleById(Long id);

    public SaleResponseDTO findSaleResponseById(Long id);

    public void updateSale(ModifySaleDTO saleDTO);

    public void deleteSale(Long id);
}
