package com.bazar_elamigo.elamigo_bazar.service.Sale;

import com.bazar_elamigo.elamigo_bazar.model.Sale;
import com.bazar_elamigo.elamigo_bazar.rest.Dto.SaleDTO;

import java.time.LocalDate;
import java.util.List;

public interface ISaleService {

    //new sale
    public void newSale(Sale sale);

    //delete sale
    public void deleteSale(Long sale_id);

    //read sale
    public Sale readSale(Long sale_id);

    //modify sale
    public void modifySale(Long sale_id);

    //read list of sales
    public List<Sale> saleList();

    //read list of sales in a day
    public List<Sale> saleListInADay(LocalDate date);

    //
    public SaleDTO readSaleToDTO(Long sale_id);

}
