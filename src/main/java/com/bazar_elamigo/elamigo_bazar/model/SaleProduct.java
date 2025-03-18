package com.bazar_elamigo.elamigo_bazar.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SaleProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    @JsonBackReference
    private Sale sale;
    private Integer quantity;
    private double price;

    public SaleProduct() {}

    public SaleProduct(Long id, Product product, Sale sale, Integer quantity, double price) {
        this.id = id;
        this.product = product;
        this.sale = sale;
        this.quantity = quantity;
        this.price = price;
    }
}
