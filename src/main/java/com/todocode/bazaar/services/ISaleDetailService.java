package com.todocode.bazaar.services;


import com.todocode.bazaar.models.Customer;
import com.todocode.bazaar.models.Sale;
import com.todocode.bazaar.models.SaleDetail;

import java.util.List;

public interface ISaleDetailService {

    public List<SaleDetail> getSalesDetails();

    public void saveSaleDetail(SaleDetail saleDetail);

    public void saveAll(List<SaleDetail> saleDetails);

    public SaleDetail findSaleDetail(Long id);

    public void editSaleDetail(SaleDetail saleDetail);

    public void deleteSaleDetail(Long id);


    public List<SaleDetail> getDetailsFromSaleId(Long id);
}
