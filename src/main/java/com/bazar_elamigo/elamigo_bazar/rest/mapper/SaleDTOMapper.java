package com.bazar_elamigo.elamigo_bazar.rest.mapper;

import com.bazar_elamigo.elamigo_bazar.model.Sale;
import com.bazar_elamigo.elamigo_bazar.model.SaleProduct;
import com.bazar_elamigo.elamigo_bazar.rest.Dto.SaleDTO;
import com.bazar_elamigo.elamigo_bazar.rest.Dto.SaleProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleDTOMapper {
    SaleDTOMapper INSTANCE = Mappers.getMapper(SaleDTOMapper.class);

    @Mapping(source = "client.name", target = "clientName")
    @Mapping(source = "client.lastName", target = "clientLastName")
    @Mapping(source = "productList", target= "detail")
    SaleDTO saleToSaleDTO(Sale sale);

    // Función para mapear cada SaleProduct a SaleProductDTO
    @Mapping(source = "product.name", target = "productName")  // Mapear nombre del producto
    @Mapping(source = "price", target = "price")  // Mapear precio
    @Mapping(source = "quantity", target = "quantity")  // Mapear cantidad
    SaleProductDTO saleProductToSaleProductDTO(SaleProduct saleProduct);

    // Mapear la lista de productos
    List<SaleProductDTO> saleProductListToSaleProductDTOList(List<SaleProduct> saleProductList);
}


