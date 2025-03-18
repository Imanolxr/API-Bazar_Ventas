package com.bazar_elamigo.elamigo_bazar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long sale_id;

    @Temporal(TemporalType.DATE)
    private LocalDate dateOfSale;

    private Double total;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<SaleProduct> productList = new ArrayList<>();






    public Sale() {
    }

    public Sale(Long sale_id, LocalDate dateOfSale, Double total, Client client, List<SaleProduct> productList) {
        this.sale_id = sale_id;
        this.dateOfSale = dateOfSale;
        this.total = total;
        this.client = client;
        this.productList = productList;
    }
}
