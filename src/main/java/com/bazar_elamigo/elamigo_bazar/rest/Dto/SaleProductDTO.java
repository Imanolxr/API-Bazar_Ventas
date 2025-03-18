package com.bazar_elamigo.elamigo_bazar.rest.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleProductDTO {
    private String productName;
    private int quantity;
    private Double price;

    public SaleProductDTO() {
    }

    public SaleProductDTO(String productName, int quantity, Double price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
}
