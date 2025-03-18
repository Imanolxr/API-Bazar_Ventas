package com.bazar_elamigo.elamigo_bazar.rest.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class SaleDTO {
    private Long sale_id;
    private LocalDate dateOfSale;
    private String clientName;
    private String clientLastName;

    @NotNull(message = "La lista no puede estar vacía")
    private List<SaleProductDTO> detail;


    private Double total;

    public SaleDTO() {
    }

    public SaleDTO(Long sale_id, LocalDate dateOfSale, String clientName, String clientLastName, List<SaleProductDTO> detail, Double total) {
        this.sale_id = sale_id;
        this.dateOfSale = dateOfSale;
        this.clientName = clientName;
        this.clientLastName = clientLastName;
        this.detail = detail;
        this.total = total;
    }
}
