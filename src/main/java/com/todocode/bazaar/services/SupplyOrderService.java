package com.todocode.bazaar.services;

import com.todocode.bazaar.dto.*;
import com.todocode.bazaar.exceptions.NotFoundException;
import com.todocode.bazaar.mapper.SupplyOrderDetailMapper;
import com.todocode.bazaar.mapper.SupplyOrderMapper;
import com.todocode.bazaar.models.SupplyOrder;
import com.todocode.bazaar.models.SupplyOrderDetail;
import com.todocode.bazaar.models.enums.Status;
import com.todocode.bazaar.repository.ISupplyOrderDetailRepository;
import com.todocode.bazaar.repository.ISupplyOrderRepository;
import com.todocode.bazaar.services.validator.SupplyOrderValidator;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class SupplyOrderService implements ISupplyOrderService {


    @Autowired
    ISupplyOrderRepository supplyOrderRepository;

    @Autowired
    ISupplyOrderDetailRepository supplyOrderDetailRepository;

    @Autowired
    SupplyOrderValidator supplyOrderValidator;

    @Autowired
    SupplyOrderMapper supplyOrderMapper;

    @Autowired
    SupplyOrderDetailMapper supplyOrderDetailMapper;

    @Autowired
    IProductService productService;


    @Override
    public List<SupplyOrderResponseDTO> getAllSupplies() {
        return supplyOrderRepository.findAll()
                .stream()
                .map(supplyOrder -> supplyOrderMapper.toDTO(supplyOrder))
                .toList();
    }

    @Override
    public SupplyOrderResponseDTO findByIdResponse(Long id) {
        return supplyOrderMapper.toDTO(supplyOrderRepository.findById(id)
                                                                .orElseThrow(() -> new NotFoundException("Supply order not found. ID: " + id)));
    }

    @Override
    public SupplyOrder findById(Long id) {
        return supplyOrderRepository.findById(id).orElse(null);
    }

    @Override
    public void createSupply(CreateSupplyOrderDTO supplyDTO) {
        supplyOrderValidator.validateCreateSupplyOrder(supplyDTO);
        SupplyOrder supplyOrder = supplyOrderRepository.save(supplyOrderMapper.toEntity(supplyDTO));

       List<SupplyOrderDetail> supplyOrderDetailList = this.processOrderDetails(supplyDTO.orderDetails(), supplyOrder);
        supplyOrderDetailRepository.saveAll(supplyOrderDetailList);

        this.updateSupplyOrderWithTotalAmount(supplyOrder, this.calculateTotalAmount(supplyOrderDetailList));
        this.replenishStockFromSupplyOrder(supplyOrderDetailList);
    }


    private List<SupplyOrderDetail> processOrderDetails(List<CreateSupplyOrderDetailDTO> supplyOrderDetailDTOList, SupplyOrder supplyOrder){
        return supplyOrderDetailDTOList
                 .stream()
                 .map(supplyOrderDetailDTO -> supplyOrderDetailMapper.toEntity(supplyOrderDetailDTO, supplyOrder))
                 .toList();
    }

    private Double calculateTotalAmount(List<SupplyOrderDetail> supplyOrderDetails){
        return supplyOrderDetails
                .stream()
                .mapToDouble(detail -> detail.getUnitPrice() * detail.getQuantity())
                .sum();
    }

    private void updateSupplyOrderWithTotalAmount(SupplyOrder supplyOrder, Double totalAmount){
        supplyOrder.setTotalSupplyCost(totalAmount);
        supplyOrder.setStatus(Status.COMPLETED);
        supplyOrderRepository.save(supplyOrder);
    }

    private void replenishStockFromSupplyOrder(List<SupplyOrderDetail> supplyOrderDetails) {
        for(SupplyOrderDetail sod : supplyOrderDetails){
            productService.replenishStock(sod.getProduct().getId(), sod.getQuantity());
        }
    }




    @Override
    public void updateSupply(ModifySupplyOrderDTO supplyDTO) {
        supplyOrderValidator.validateUpdateSupplyOrder(supplyDTO);
        supplyOrderRepository.save(supplyOrderMapper.update(this.findById(supplyDTO.id()), supplyDTO));
    }


    @Override
    public void deleteSupply(Long id) {
       SupplyOrder supplyOrderAux = supplyOrderRepository.findById(id).orElseThrow(
                                                        () -> new NotFoundException("Supply order not found. ID: " + id));

       supplyOrderAux.setStatus(Status.DELETED);
       supplyOrderRepository.save(supplyOrderAux);
    }
}
