package com.bazar_elamigo.elamigo_bazar.service.SaleProduct;

import com.bazar_elamigo.elamigo_bazar.exception.ProductNotFoundException;
import com.bazar_elamigo.elamigo_bazar.rest.Dto.SaleProductDTO;
import com.bazar_elamigo.elamigo_bazar.model.SaleProduct;
import com.bazar_elamigo.elamigo_bazar.repository.Product.IProductRepository;
import com.bazar_elamigo.elamigo_bazar.repository.SaleProduct.ISaleProductRepository;
import com.bazar_elamigo.elamigo_bazar.rest.mapper.SaleProductDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleProductService implements ISaleProductService{

    @Autowired
    private SaleProductDTOMapper saleProductDTOMapper;
    @Autowired
    ISaleProductRepository saleProdRepo;
    @Autowired
    IProductRepository productRepo;


    @Override
    public List<SaleProductDTO> getDetails() {
        List<SaleProductDTO> detailDTO = new ArrayList<>();
        List<SaleProduct> saleProductList = saleProdRepo.findAll();
        for(SaleProduct saleprod : saleProductList){
            SaleProductDTO dto = saleProductDTOMapper.toDTO(saleprod);
            detailDTO.add(dto);
        }
        return detailDTO;
    }

    @Override
    public SaleProduct findSaleProduct(Long saleProduct_id) {
        return saleProdRepo.findById(saleProduct_id).orElseThrow(() -> new ProductNotFoundException("Producto no encontrado con el id: " + saleProduct_id));
    }

    @Override
    public SaleProductDTO readSaleDto(Long saleProduct_id) {
        SaleProduct saleprod = saleProdRepo.findById(saleProduct_id).orElseThrow(() -> new ProductNotFoundException("Producto no encontrado con el id: " + saleProduct_id));
        return saleProductDTOMapper.toDTO(saleprod);
    }

    @Override
    @Transactional
    public void SaveProduct(SaleProduct saleProduct) {
        saleProdRepo.save(saleProduct);
    }

    @Override
    @Transactional
    public void deleteSaleProduct(Long saleProduct_id) {
        saleProdRepo.deleteById(saleProduct_id);

    }

    @Override
    @Transactional
    public void editSaleProduct(Long saleProduct_id, SaleProductDTO detailDTO) {

    }
}
