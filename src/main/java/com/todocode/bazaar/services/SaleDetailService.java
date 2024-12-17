package com.todocode.bazaar.services;

import com.todocode.bazaar.models.SaleDetail;
import com.todocode.bazaar.repository.ISaleDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleDetailService implements ISaleDetailService{

    @Autowired
    ISaleDetailRepository saleDetailRepository;

    @Override
    public List<SaleDetail> getSalesDetails() {
        return saleDetailRepository.findAll();
    }

    @Override
    public void saveSaleDetail(SaleDetail saleDetail) {
        saleDetailRepository.save(saleDetail);
    }

    @Override
    public void saveAll(List<SaleDetail> saleDetails){
        saleDetailRepository.saveAll(saleDetails);
    }

    @Override
    public SaleDetail findSaleDetail(Long id) {
        return saleDetailRepository.findById(id).orElse(null);
    }

    @Override
    public List<SaleDetail> getDetailsFromSaleId(Long id){
        return saleDetailRepository.findBySaleId(id);
    }

    @Override
    public void editSaleDetail(SaleDetail saleDetail) {
        saleDetailRepository.save(saleDetail);
    }

    @Override
    public void deleteSaleDetail(Long id) {
        saleDetailRepository.deleteById(id);
    }
}
