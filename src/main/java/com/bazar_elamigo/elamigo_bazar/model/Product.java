package com.bazar_elamigo.elamigo_bazar.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "product_id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    @Size(min = 2, max = 60)
    @NotBlank(message = "el campo nombre no puede estar en blanco")
    private String name;

    @NotBlank(message = "el campo marca no puede estar en blanco")
    private String brand;

    @PositiveOrZero(message = "el costo debe ser mayor o igual a cero")
    @NotNull(message = "el campo precio no puede estar en blanco")
    private Double price;

    @PositiveOrZero(message = "el stock debe ser mayor o igual a cero")
    @NotNull(message = "el campo stock no puede estar en blanco")
    private Double availableQuantity;

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    @JsonIgnore
    private List<SaleProduct> saleProductList;

    public Product() {
    }

    public Product(Long product_id, String name, String brand, Double price, Double availableQuantity) {
        this.product_id = product_id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }
}
