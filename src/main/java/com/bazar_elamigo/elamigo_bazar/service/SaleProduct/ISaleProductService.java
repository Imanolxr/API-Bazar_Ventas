package com.bazar_elamigo.elamigo_bazar.service.SaleProduct;

import com.bazar_elamigo.elamigo_bazar.rest.Dto.SaleProductDTO;
import com.bazar_elamigo.elamigo_bazar.model.SaleProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ISaleProductService {

    public List<SaleProductDTO> getDetails();

    public SaleProduct findSaleProduct(Long saleProduct_id);
    public SaleProductDTO readSaleDto(Long saleProduct_id);
    public void SaveProduct(SaleProduct saleProduct);
    public void deleteSaleProduct(Long saleProduct_id);
    public void editSaleProduct(Long saleProduct_id, SaleProductDTO detailDTO);

}
