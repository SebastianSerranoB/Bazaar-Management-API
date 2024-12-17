package com.todocode.bazaar.services;

import com.todocode.bazaar.dto.CreateSaleDTO;
import com.todocode.bazaar.dto.CreateSaleDetailDTO;
import com.todocode.bazaar.dto.ModifySaleDTO;
import com.todocode.bazaar.dto.SaleResponseDTO;
import com.todocode.bazaar.exceptions.NotFoundException;
import com.todocode.bazaar.mapper.SaleDetailMapper;
import com.todocode.bazaar.mapper.SaleMapper;
import com.todocode.bazaar.models.Sale;
import com.todocode.bazaar.models.SaleDetail;
import com.todocode.bazaar.models.enums.Status;
import com.todocode.bazaar.repository.ISaleRepository;
import com.todocode.bazaar.repository.ISupplyOrderDetailRepository;
import com.todocode.bazaar.services.validator.SaleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService implements ISaleService{

    @Autowired
    ISaleRepository saleRepository;


    @Autowired
    SaleMapper saleMapper;

    @Autowired
    SaleDetailMapper saleDetailMapper;

    @Autowired
    SaleValidator saleValidator;

    @Autowired
    ISaleDetailService saleDetailService;

    @Autowired
    IProductService productService;




    @Override
    public List<SaleResponseDTO> getAllSales() {
        return saleRepository.findAll().stream()
                                .map(sale -> saleMapper.toResponseDTO(sale))
                                .toList();
    }


    @Override
    public void createSale(CreateSaleDTO saleDTO) {

        saleValidator.validateCreateSale(saleDTO);
        Sale sale = saleRepository.save(saleMapper.toEntity(saleDTO));

        List<SaleDetail> saleDetails = this.processSaleDetails(saleDTO.saleDetails(), sale);
        saleDetailService.saveAll(saleDetails);

        this.updateSaleWithTotalAmount(sale, calculateTotalAmount(saleDetails));

        this.deductStockForSaleDetails(saleDetails);
    }

    private List<SaleDetail> processSaleDetails(List<CreateSaleDetailDTO> saleDetailDTO, Sale sale){
        return saleDetailDTO.stream()
                .map(detailDTO -> saleDetailMapper.toEntity(detailDTO, sale))
                .toList();
    }

    private Double calculateTotalAmount(List<SaleDetail> saleDetails){
        return saleDetails.stream()
                .mapToDouble(detail -> detail.getSalePrice() * detail.getProductQuantity())
                .sum();
    }

    private void updateSaleWithTotalAmount(Sale sale, Double totalAmount){
        sale.setTotalAmount(totalAmount);
        sale.setStatus(Status.COMPLETED);
        saleRepository.save(sale);
    }

    private void deductStockForSaleDetails(List<SaleDetail> saleDetails) {
        for(SaleDetail sd : saleDetails){
            productService.deductStock(sd.getProduct().getId(), sd.getProductQuantity());
        }
    }


    @Override
    public Sale findSaleById(Long id) {
        return  saleRepository.findById(id).orElse(null);
    }

    @Override
    public SaleResponseDTO findSaleResponseById(Long id) {
        Sale saleAux = saleRepository.findById(id)
                                        .orElseThrow( () -> new NotFoundException("Sale not found. ID: " + id));

        return  saleMapper.toResponseDTO(saleAux);
    }

    @Override
    public void updateSale(ModifySaleDTO saleDTO) {
        saleValidator.validateUpdateSale(saleDTO);
        saleRepository.save(saleMapper.updateEntity(this.findSaleById(saleDTO.id()), saleDTO));
    }

    @Override
    public void deleteSale(Long id) {
        Sale saleAux = saleRepository.findById(id).orElseThrow( () -> new NotFoundException("Sale not found. ID: " + id));
        saleAux.setStatus(Status.DELETED);
        saleRepository.save(saleAux);
    }


}
