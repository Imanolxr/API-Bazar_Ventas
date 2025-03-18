package com.bazar_elamigo.elamigo_bazar.rest.mapper;

import com.bazar_elamigo.elamigo_bazar.model.SaleProduct;
import com.bazar_elamigo.elamigo_bazar.rest.Dto.SaleProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper (componentModel = "spring")
public interface SaleProductDTOMapper {
    SaleProductDTOMapper INSTANCE = Mappers.getMapper(SaleProductDTOMapper.class);


    SaleProductDTO toDTO(SaleProduct saleProduct);


}
