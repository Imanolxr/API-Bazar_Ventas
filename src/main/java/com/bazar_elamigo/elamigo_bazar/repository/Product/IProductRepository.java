package com.bazar_elamigo.elamigo_bazar.repository.Product;

import com.bazar_elamigo.elamigo_bazar.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
}
