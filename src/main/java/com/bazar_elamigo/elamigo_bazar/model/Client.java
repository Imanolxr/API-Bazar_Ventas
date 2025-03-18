package com.bazar_elamigo.elamigo_bazar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long client_id;

    @NotBlank(message = "El nombre no puede estar en blanco")
    private String name;

    @NotBlank(message = "El apellido no puede estar en blanco")
    private String lastName;

    @NotBlank(message = "El dni no puede estar en blanco")
    private String dni;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Sale> saleList;

    public Client() {
    }

    public Client(Long client_id, String name, String lastName, String dni, List<Sale> saleList) {
        this.client_id = client_id;
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.saleList = saleList;
    }
}
